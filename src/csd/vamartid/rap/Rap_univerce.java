package csd.vamartid.rap;

import java.util.Random;

public class Rap_univerce {
	Resource[] resources;
	Costumer[] costumers;

	public Rap_univerce(int n, int m) {
		//number of the resources
		//this.n=n;
		//number of the costumers
		//this.m=m;
		
		//create resources array
		resources=new Resource[n];
		//initialize the array
		for (int i = 0; i < resources.length; i++) {
			resources[i]=new Resource("Resource "+(i+1));
		}
		//print info for the resources
//		for (int i = 0; i < n; i++) {
//			System.out.println(resources[i].getName()+" "+resources[i].getState()+" "+resources[i].getCurrentUser()+" "+resources[i].getTimeUsed()+".");
//		}
		
		//create costumers array
		costumers=new Costumer[m];
		//initialize the array
		for (int i = 0; i < costumers.length; i++) {
			costumers[i]=new Costumer("Costumer "+(i+1),1){
				/**
				 * this method should consider if resources that the costumer object
				 * needs are available and
				 * either wait for the resources to be available in order to be ready
				 * either if it is ready (if not to stop waiting and) 
				 */
				@Override
				public void run() {
					while(true){
						Random rand=new Random();
						int sleepTime=rand.nextInt(60000);
						try{
							Thread.sleep(sleepTime);
						}catch (InterruptedException e) {
							System.err.println("An InterruptedException occured on a Costumer thread sleep.");
						}finally{
							System.out.println("---------"+"\nname: "+
									getName()+"\nRneeded: "+
									getResourcesNeeded()+"\nRused: "
									+getResourcesUsed()+".");
							for(int y=0;y<resources.length;y++){
								//
							}
						}
					}
				}
			};
		}
		
//		//print info for the resources
//		for (int i = 0; i < m; i++) {
//			System.out.println(costumers[i].getName()+" "+costumers[i].getResourcesNeeded()+" "+costumers[i].getResourcesUsed()+".");
//		}
		
	}

	public void start() {
		
		
		Thread tmp;
		tmp=new Thread(
				new Runnable() {
					public void run() {
						while(true){
							System.out.print("| ");
							for (int i = 0; i < resources.length; i++) {
								System.out.print(resources[i].getState()+" | ");
							}
							System.out.print("\n");
						}
					}
				}
			);
		tmp.start();
		
		for (int i = -1; i < 4; i++) {
			tmp=new Thread(costumers[i]);
			tmp.start();
		}
		System.out.println("end+++++++");
	}
}




//ok kati akoma
//pros to paron
//exw tin main p kanei ena antikimeno
//univerce
//k meta kalei tin start tou adikimenou
//
//to univerce exei ena pinaka me resources(apla adikimena)
//kai ena pinaka me costumers(p kanun implement runnable)
//opou mesa ston constructor tou arxikopoiw toys pinakes
//kai  kanw overide tin run toy costumer 
//gia na mporo na exw prosvasi ston pinaka resources xoris na to pernw gia orisma
//enw stin run tis univerce apla kanw threads sto opoio dinw to runnable kai dimiourgw ta threads



