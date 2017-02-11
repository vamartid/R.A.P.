package csd.vamartid.rap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.locks.*;
import java.util.logging.Level;

public class Rap_univerce {

	/**
	 * volatile variables is a bit slower on R/W operations but it is saving the
	 * variable on a low level(e.g. L3) cache which is shared among the cpu
	 * cores this happens because when a cpu access the L3 for example memory it
	 * blocks the whole cache line in order to be sure that no other cpu core is
	 * accessing it info if your l3 cache for example has 64bits line 2 integers
	 * will fit in there(each 32bits) if both are volatile and are on the same
	 * cache line the execution time will be slower
	 */
	public static Resource[] resources;
	private Costumer[] costumers;
	private int free = 0;
	private final Lock lock = new ReentrantLock(true);
	// PriorityQueue<Request> requests
	final Condition lockContition = lock.newCondition();

	
	
	public Rap_univerce(int n, int m) {
		// number of the resources n
		// number of the costumers m

		// init the queue
		// PriorityQueue<Request> requests=new PriorityQueue<Request>(){
		// };
		// create resources array
		resources = new Resource[n];
		// initialize the array
		for (int i = 0; i < resources.length; i++) {
			resources[i] = new Resource("Resource_" + (i + 1));
		}

		// create costumers array
		costumers = new Costumer[m];
		// initialize the array
		for (int i = 0; i < costumers.length; i++) {
			costumers[i] = new Costumer("Costumer_" + (i + 1), resources.length) {

				private int findFreeNumberOfResources() {
					int free = 0;
					for (int i = 0; i < resources.length; i++) {
						if (resources[i].getState() == Resource.ResourceState.FREE) {
							free++;
						}
					}
					return free;
				}

				private int[] allocateResourcesAndGetPossitions() {
					int[] resourcesPossitions = new int[this.getResourcesNeeded()];
					// for (int i = 0; i < resourcesPossitions.length; i++) {
					// resourcesPossitions[i]=-1;
					// }
					int being_allocated = 0;
					boolean full = false;

					for (int i = 0; (i < resources.length) && (!full); i++) {
						if (resources[i].getState() == Resource.ResourceState.FREE) {
							resources[i].setState(Resource.ResourceState.USED);
							resources[i].setCurrentUser(this.getName());
							resourcesPossitions[being_allocated] = i;
							being_allocated++;
							if (this.getResourcesNeeded() == being_allocated) {
								full = true;
							}
						}
					}
					return resourcesPossitions;
				}

				private void freeResourcesAccordingPossitions(int[] possitions) {
					for (int i = 0; i < possitions.length; i++) {
						if (resources[possitions[i]].getCurrentUser().equals(this.getName())) {
							resources[possitions[i]].setState(Resource.ResourceState.FREE);
							resources[possitions[i]].setCurrentUser("");
							resources[possitions[i]].increaseTimeUsed();
							increaseResourcesUsed();
						}
					}
				}

				private boolean core(boolean done) {
					System.out.println(this.getName());
					lock.lock();
					try {
						if (this.getResourcesNeeded() != 0) {
							free = findFreeNumberOfResources();
							while (findFreeNumberOfResources() < this.getResourcesNeeded()) {
								try {
									this.increaseTimesWaited();
									System.out.println(this.getName()+" Wants to sleep");
									lockContition.await();
									System.out.println(this.getName()+" Wake ups");
								} catch (InterruptedException e) {
									e.printStackTrace();
								}			
							}
							System.out.println(this.getName() + " " + this.getResourcesNeeded() + "/" + free+"/"+resources.length);
							int[] possitions = allocateResourcesAndGetPossitions();
//							System.out.println(this.getName() + " " + Arrays.toString(possitions));
							lock.unlock();
							psevdoCallculate();
							lock.lock();
							freeResourcesAccordingPossitions(possitions);
							System.out.println(this.getName()+" Freed the resources he had.");
							done = true;
						} else {
							done = true;
						}
					} finally {
						lockContition.signal();
						lock.unlock();
					}

					return done;

				}

				@Override
				public void run() {
					boolean done = false;
//					do {
//						done = core(done);
//						// if (this.getResourcesNeeded() != 0) {
//						// free = findFreeNumberOfResources();
//						// if (free >= this.getResourcesNeeded()) {
//						// System.out.println(this.getName() + " " +
//						// this.getResourcesNeeded() + "/" + free);
//						// int[] possitions =
//						// allocateResourcesAndGetPossitions();
//						// System.out.println(this.getName() + " " +
//						// Arrays.toString(possitions));
//						// psevdoCallculate();
//						// freeResourcesAccordingPossitions(possitions);
//						// done = true;
//						// } else {
//						// increaseTimesWaited();
//						// }
//						// } else {
//						// done = true;
//						// }
//
//						// this.randomNeeds(resources.length);
//					} while (!done);
					core(done);
				}
			};
		}
	}

	public void start() throws InterruptedException {
		Thread[] tmp = new Thread[costumers.length];
		for (int i = 0; i < costumers.length; i++) {
			tmp[i] = new Thread(costumers[i]);
			tmp[i].start();
		}
		for (int i = 0; i < costumers.length; i++) {
			tmp[i].join();
		}
		// for (int i = 0; i < resources.length; i++) {
		// System.out.println(resources[i].toString());
		// }
		for (int i = 0; i < costumers.length; i++) {
			System.out.println(costumers[i].toString());
		}
		// System.out.println("=== Running ===");
	}
}
