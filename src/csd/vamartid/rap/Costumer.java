package csd.vamartid.rap;

import java.util.Random;

public class Costumer implements Runnable{
	//Resource Object fields
	private int resourcesNeed;//resources he needs now
	private int resourcesUsed;//resources he has used all time
	private String name;//name of the costumer
	
	
	/**
	 * the constructor of a costumer object which assigns him 
	 * with a name and a number of his needs and resets resoucesUsed value
	 * @param name
	 * @param resourcesNeed
	 */
	public Costumer(String name,int resourcesNeed) {
		//set a name
		setName(name);
		//set a number of the resources the costumer needs
		setResourcesNeeded(resourcesNeed);
		//reset the number of all the resources the costumer will use 
		setResourcesUsed(0);
		
	}
	
	/**
	 * set the name of the costumer
	 * @param name
	 */
	private void setName(String name){
		this.name=name;
	}
	
	/**
	 * get the name of the costumer
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set the resource needed number
	 * @param resourceNeed
	 */
	public void setResourcesNeeded(int resourceNeed){
		this.resourcesNeed=resourceNeed;
	}
	
	/**
	 * get the resource needed number
	 */
	public int getResourcesNeeded(){
		return resourcesNeed;
	}
	
	/**
	 * set the resource used number
	 * @param resourceUsed
	 */
	private void setResourcesUsed(int resourceUsed){
		this.resourcesUsed=resourceUsed;
	}
	
	/**
	 * get the resource used number
	 */
	public int getResourcesUsed(){
		return resourcesUsed;
	}
	
	/**
	 * this method is implemented in the class 
	 * Rap_univerce which Overrides it 
	 */
	@Override
	public void run() {}
	
}
