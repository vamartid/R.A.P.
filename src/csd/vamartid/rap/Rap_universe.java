package csd.vamartid.rap;

//import java.util.Arrays;
import java.util.concurrent.locks.*;

/**
 * Rap_universe object contains the resources and the costumers which will
 * interact each other a lock which has to do with which thread is in the
 * Critical sector and an integer about how many resources are available and it
 * is being changed each time a thread ask for resources
 *
 * @author vamartid
 *
 * This project is under the GNU GPLV3 for more info check the LICENSE file.
 *
 */
public class Rap_universe {

	/**
	 * 
	 * INFO ABOUT VOLATILE VARIABLES:
	 * 
	 * Volatile variables is a bit slower on R/W operations but it is saving the
	 * variable on a low level(e.g. L3) cache which is shared among the cpu
	 * cores this happens because when a cpu access the L3 for example memory it
	 * blocks the whole cache line in order to be sure that no other cpu core is
	 * accessing it info if your l3 cache for example has 64bits line 2 integers
	 * will fit in there(each 32bits) if both are volatile and are on the same
	 * cache line the execution time will be slower
	 */
	
	private static Resource[] resources;
	private Costumer[] costumers;
	private int free = 0;
	private final Lock lock = new ReentrantLock(true);
	// PriorityQueue<Request> requests
	final Condition lockContition = lock.newCondition();

	/**
	 * Rap_universe Constructor creates the objects of the resources by giving
	 * each cell of the array of the resources a new resources object
	 * 
	 * Same goes for the Costumer runnable object but in their case it also
	 * overrides it's run method (in their own class run method is empty) This
	 * implementation has to do with the fact I wanted resources array to be
	 * private and not public.
	 * 
	 * I could make the array public and just do the run method on the Costumer
	 * class but i preferred not to.
	 * 
	 * It also creates some other functions which are used on the run method of
	 * the costumer objects
	 * 
	 * Each time a thread want's to access the resources it acquires the lock
	 * 
	 * If the number of the resources it needs is equal to zero it does not have
	 * access to the resources Finally the thread send a signal to the other
	 * threads to acquire the lock and let the lock to the other threads
	 * 
	 * If the number of the resources is not zero the thread checks how many
	 * resources are free if there are not enough resources free it waits till
	 * it wake up from a signal. When it wakes up it check again for the free
	 * resources. (continues that loop) If the thread manages to acquire the
	 * resources it needs it allocates the first Free resources available on the
	 * array and saves their position to a temporary array. This happen in order
	 * not to check all the array of the resources again in order to free it's
	 * own,so this way it know which to check. When it has acquire the resources
	 * it unlocks and Psevdocalculate (Which means it execute the job that these
	 * resources were needed for) When it ends it acquaires the lock again and
	 * free the resources it had allocated Finally the thread send a signal to
	 * the other threads to acquire the lock and let the lock to the other
	 * threads
	 * 
	 * @param n
	 *            number of resources objects
	 * @param m
	 *            number of thread objects
	 */
	public Rap_universe(int n, int m) {
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
					System.out.println(this.getName() + " is on the store asking for resources.");
					lock.lock();
					try {
						if (this.getResourcesNeeded() != 0) {
							free = findFreeNumberOfResources();
							while (free < this.getResourcesNeeded()) {
								try {
									this.increaseTimesWaited();
									System.out.println(this.getName() + " Wants to sleep.");
									lockContition.await();
									System.out.println(this.getName() + " Wake ups.");
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								free = findFreeNumberOfResources();
							}
							System.out.println(this.getName() + " is taking " + this.getResourcesNeeded() + " of the "
									+ free + " free from all " + resources.length + " resources.");
							int[] possitions = allocateResourcesAndGetPossitions();
							// System.out.println(this.getName() + " " +
							// Arrays.toString(possitions));
							lock.unlock();
							psevdoCallculate();
							lock.lock();
							freeResourcesAccordingPossitions(possitions);
							System.out.println(this.getName() + " Freed the resources he had.");
							done = true;
						} else {
							System.out.println(this.getName() + " Did not need resources.");
							done = true;
						}
					} finally {
						lockContition.signal();
						lock.unlock();
//						this.randomNeeds(resources.length);
//						done=!true;
					}

					return done;

				}

				@Override
				public void run() {
					boolean done = false;
//					do{
						done=core(done);
//					}while(!done);
				}
			};
		}
	}

	/**
	 * Start method creates a new thread for each costumer created and start it.
	 * It joins them in order to print all the info of the threads after their
	 * job has been done.
	 *
	 * @throws InterruptedException
	 */
	public void start() throws InterruptedException {
		System.out.println("|####[>On the run<]####|");
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
		System.out.println("|####[>Stats<]####|");
		for (int i = 0; i < costumers.length; i++) {
			System.out.println(costumers[i].toString());
		}
		// System.out.println("=== Running ===");
	}
}
