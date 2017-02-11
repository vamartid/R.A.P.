package csd.vamartid.rap;

public class Resource {
    //Resource Object fields

    private ResourceState state;
    private int timesUsed;
    private String user;
    private String name;

    /**
     * set an enumeration value for used or free
     *
     */
    public enum ResourceState {
        USED, FREE
    }

    /**
     * constructor of a resource set the resource as free set it's time used
     * value as zero set it's current user-owner value to null
     */
    public Resource(String name) {
        this.state = ResourceState.FREE;
        this.timesUsed = 0;
        this.user = "";
        this.name = name;
    }

    /**
     * get the name of the resource
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get resource state
     *
     * @return
     */
    public ResourceState getState() {
        return this.state;
    }

    /**
     * set resource state to the given new state (used or free)
     *
     * @param new_state
     */
    public void setState(ResourceState new_state) {
        this.state = new_state;
    }

    /**
     * increase the timeUsed value over 1
     */
    public void increaseTimeUsed() {
        timesUsed++;
    }

    /**
     * getter for timesUsed
     */
    public int getTimeUsed() {
        return timesUsed;
    }

    /**
     * set the currentUser
     *
     * @param user
     */
    public void setCurrentUser(String user) {
        this.user = user;
    }

    /**
     * get the currentUser
     */
    public String getCurrentUser() {
        return user;
    }
    
    public String toString(){
        return "R-> "+"| "+ getName() +
                " | " + getState() +
                " | User " + getCurrentUser() +
                " | Used " + getTimeUsed();
                /*"RESOURCE-> "+" Name: "+ getName() +
                " State " + getState() +
                " User " + getCurrentUser() +
                " TimesUsed " + getTimeUsed();*/
    }
//    /**
//     * get the resource set the resource state property to used use increase the
//     * number which gives the times a resource was used free set the resource
//     * state to free i order other costumers can use it
//     */
//    public void getUseAndFree() {
//        //set the resource as used
//        setState(ResourceState.USED);
//        //use
//        increaseTimeUsed();
//        //set the resource as free
//        setState(ResourceState.FREE);
//    }
}
