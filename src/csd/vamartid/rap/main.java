package csd.vamartid.rap;
//imports
/**
 * main class contains the main function of the program
 * @author vamartid
 *
 */
public class main {
	/**
	 * this class is the main function of the program
	 * it should create an array of resources
	 * and and array of costumers(threads) which will
	 * use them with random order
	 * each thread is able to use some resources
	 * each thread should wait if the number of the resources it needs is not available
	 * and 
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		Rap_univerce universe=new Rap_univerce(10,20);
		universe.start();
	}

}
