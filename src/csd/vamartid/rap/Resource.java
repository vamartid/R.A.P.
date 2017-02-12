package csd.vamartid.rap;
/**
 * Resource object
 * has a state (FREE of USED)
 * used is when it's alocated from a costumer runnable
 * free is when it's not
 * a times used which represents how many times the object has been alocated
 * a user which is the name of the user that is currently using it
 * and last a name which is the name of the resource
 * 
 * @author vamartid
 *
 * This project is under the GNU GPLV3 for more info check the LICENSE file.
 *
 */
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
    
    /**
     * prints info of the resource
     */
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

}
