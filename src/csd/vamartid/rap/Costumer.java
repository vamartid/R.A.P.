package csd.vamartid.rap;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Costumer has these fields
 * at first resourcesNeed which is the number of the resources it needs to do it's job
 * secondly the resourcesUsed which is the number of the total resources it used
 * name which is the costumer name
 * timesWaited which is the number of the times it needed to wait of lack of free resources.
 * 
 * @author vamartid
 *
 * This project is under the GNU GPLV3 for more info check the LICENSE file.
 *
 */
public class Costumer implements Runnable {
    //Resource Object fields

    private int resourcesNeed;//resources he needs now
    private int resourcesUsed;//resources he has used all time
    private String name;//name of the costumer
    private int timesWaited;//times the costumer left someone else go first
    
    /**
     * the constructor of a costumer object which assigns him with a name and
     * the max number of the resources available' in order it will produce a
     * random number of the resources the costumer needs. Resets resoucesUsed
     * value
     *
     * @param name
     * @param resourcesNeed
     */
    public Costumer(String name, int maxResources) {
        Random rand = new Random();
        int resourcesNeed = rand.nextInt(maxResources+1);
        //set a name
        setName(name);
        //set a number of the resources the costumer needs
        setResourcesNeeded(resourcesNeed);
        //reset the number of all the resources the costumer will use 
        setResourcesUsed(0);
        resetTimesWaited();
    }

    /**
     * set the name of the costumer
     *
     * @param name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * get the name of the costumer
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set the resource needed number
     *
     * @param resourceNeed
     */
    public void setResourcesNeeded(int resourceNeed) {
        this.resourcesNeed = resourceNeed;
    }

    /**
     * get the resource needed number
     */
    public int getResourcesNeeded() {
        return resourcesNeed;
    }

    /**
     * set the resource used number
     *
     * @param resourceUsed
     */
    public void setResourcesUsed(int resourceUsed) {
        this.resourcesUsed = resourceUsed;
    }
    public void increaseResourcesUsed() {
        this.resourcesUsed++;
    }
    /**
     * get the resource used number
     */
    public int getResourcesUsed() {
        return resourcesUsed;
    }

    /**
     * get the resource used number
     */
    public String toString(){
        return "C-> "+"| " + getName() +
                " | Need: " + getResourcesNeeded()+
                " | Waited: " + getTimesWaited() +
                " | Used: " + getResourcesUsed();
                /*"COSTUMER-> "+" Name: " + getName() +
                " Rneeded: " + getResourcesNeeded()+
                " Twaited: " + getTimesWaited() +
                " Rused: " + getResourcesUsed();*/
    }
    
    /**
     * reset the times waited variabe
     */
    public void resetTimesWaited() {
        this.timesWaited = 0;
    }
    
    /*
     * increase the timesWaited by one
     */
    public void increaseTimesWaited() {
        this.timesWaited++;
    }
    
    /**
     *  returns the timesWaited
     * @return
     */
    public int getTimesWaited() {
        return timesWaited;
    }
    
    /**
     * set a new value for the resourcesNeed variable
     * @param num
     */
    public void randomNeeds(int num) {
        Random randomObj=new Random();
        setResourcesNeeded(randomObj.nextInt(num));
    }
    
    /**
     * generate a number up to 200
     * and sleeps for it
     * this action represent the calculation
     * that the thread needed the resources for
     */
    public void psevdoCallculate() {
        Random random_object=new Random();
        int sleepTime = random_object.nextInt(200);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.err.println(this.getName()+" couldn't psevdoCallculate(sleep).");
            Logger.getLogger(Costumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * this method is implemented in the class Rap_univerce which Overrides it
     */
    @Override
    public void run() {}

}
