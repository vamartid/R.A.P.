package csd.vamartid.rap;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rap_univerce {
    
    /**volatile variables
     * is a bit slower on R/W operations
     * but it is saving the variable on a 
     * low level(e.g. L3) cache which is shared 
     * among the cpu cores
     * this happens because when 
     * a cpu access the L3 for example memory
     * it blocks the whole cache line 
     * in order to be sure that no other
     * cpu core is accessing it
     * info
     * if your l3 cache for example has 64bits line
     * 2 integers will fit in there(each 32bits)
     * if both are volatile and are on the same cache line
     * the execution time will be slower
     */
    volatile Resource[] resources;
    static Costumer[] costumers;
//    PriorityQueue<Request> requests
        
    public Rap_univerce(int n, int m) {
        //number of the resources n
        //number of the costumers m
        
        //init the queue
//        PriorityQueue<Request> requests=new PriorityQueue<Request>(){
            
//        };
        
        //create resources array
        resources = new Resource[n];
        //initialize the array
        for (int i = 0; i < resources.length; i++) {
            resources[i] = new Resource("Resource " + (i + 1));
        }
        
        //create costumers array
        costumers = new Costumer[m];
        //initialize the array
        for (int i = 0; i < costumers.length; i++) {
            costumers[i] = new Costumer("Costumer " + (i + 1), resources.length) {
                /**
                 * this method should consider if resources that the costumer
                 * object needs are available and either wait for the resources
                 * to be available in order to be ready either if it is ready
                 * (if not to stop waiting and)
                 */
                @Override
                public void run() {
                    //make the request
    //                        requests.add(new Request(this));
                    boolean done=false;
                    do{
                        int free=0;
//                        synchronized(resources){
                            for(int j=0;j<resources.length;j++){
                                if(resources[j].getState()==Resource.ResourceState.FREE){
                                    free++;
                                }
                            }
//                        }
                        if(free>=this.getResourcesNeeded()){
//                            int[] resourcesPos = new int[this.getResourcesNeeded()];
//                            int pos=0;
                            int allocated=0;
                            boolean full=false;
                            System.out.println("*"+getName());
                            for(int j=0;(j<resources.length)&&(!full);j++){
                                if(resources[j].getState()==Resource.ResourceState.FREE){
                                    resources[j].setState(Resource.ResourceState.USED);
                                    resources[j].setCurrentUser(this.getName());
//                                    resourcesPos[pos]=j;
                                    System.out.println(resources[j].toString());
//                                    pos++;
                                    allocated++;
                                    if(this.getResourcesNeeded()==allocated){
                                        full=true;
                                    }
                                }
                            }
                            System.out.println("_"+getName());
                            for(int j=0;j<resources.length;j++){
                                if(resources[j].getCurrentUser()==this.getName()){
                                    resources[j].increaseTimeUsed();
                                    resources[j].setState(Resource.ResourceState.FREE);
                                    resources[j].setCurrentUser("");
                                    System.out.println(resources[j].toString());
                                }
                            }
                            this.setResourcesUsed(this.getResourcesNeeded()+this.getResourcesUsed());
                            System.out.println("+"+getName());
                            System.out.println(this.toString());
                            done=true;
//                            this.notifyAll();
                            this.randomNeeds(resources.length);
                        }else{
//                            synchronized(this){
//                                try {
//                                    this.wait();
//                                } catch (InterruptedException ex) {
//                                    System.err.println("An InterruptedException occured on a Costumer thread run while trying to wait.");
//                                }
//                            }
                        }
                    }while(true);
                }
            };
        }
    }

    public void start() throws InterruptedException {
        Thread[] tmp=new Thread[costumers.length];
        for (int i = 0; i < costumers.length; i++) {
            tmp[i] = new Thread(costumers[i]);
            tmp[i].start();
        }
        for (int i = 0; i < costumers.length; i++) {
            tmp[i].join();
        }
        for (int i = 0; i < resources.length; i++) {
            System.out.println(resources[i].toString());
        }
        System.out.println("=== Running ===");
    }
}