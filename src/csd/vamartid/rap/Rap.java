package csd.vamartid.rap;
//imports
import java.util.Scanner;

/**
 * Rap class contains the main function of the program
 * Generally it creates the Universe(Rap_Universe object)
 * Where the resources and the costumers are created,
 * and interact each other.
 *
 * @author vamartid
 *
 * This project is under the GNU GPLV3 for more info check the LICENSE file.
 *
 */
public class Rap {

    private static Scanner sc;

    /**
     * this class is the main function of the program
     * it gets input of the number of the resources and the costumer threads
     * and it creates the universe where the thread's actions take place
     *
     * @param args
     */
    public static void main(String[] args) {
    	//taking two inputs and checks if the second one
    	//which are the number of the costumers are bigger than the number
    	//of the resources
    	sc = new Scanner(System.in);
        System.out.print("Enter number of resources: ");
        while (!sc.hasNextInt()) sc.next();
        int resourcesNum = sc.nextInt();
        int costumersNum;
        System.out.print("Enter number of costumers: ");
        do {
            while (!sc.hasNextInt()) sc.next();
            costumersNum = sc.nextInt();
        } while (costumersNum < resourcesNum);
        System.out.println(resourcesNum + " " + costumersNum);
    	
	//after taking the inputs
        //create the universe with these parameters
    	try{
    		Rap_universe universe = new Rap_universe(resourcesNum, costumersNum);
            universe.start();
    	}catch (Exception e) {
    		System.err.println("InterruptedException occured");
		}
        
    }

}
