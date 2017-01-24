package csd.vamartid.rap;

import java.util.Random;

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

    public void resetTimesWaited() {
        this.timesWaited = 0;
    }
    
    public void increaseTimesWaited() {
        this.timesWaited++;
    }
    public int getTimesWaited() {
        return timesWaited;
    }
    
    
    public void randomNeeds(int num) {
        Random randomObj=new Random();
        setResourcesNeeded(randomObj.nextInt(num));
    }
    
    /**
     * this method is implemented in the class Rap_univerce which Overrides it
     */
    @Override
    public void run() {}
}
