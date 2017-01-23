/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd.vamartid.rap;

/**
 *
 * @author vamartid
 */
class Request implements Comparable<Request>{
    Costumer runnable;

    public Request(Costumer runnable) {
        this.runnable=runnable;
    }
    
    public Costumer getCostumer(){
        return runnable;
    }
    
    public boolean equals(Request other){
        return this.getCostumer().getResourcesNeeded()==other.getCostumer().getResourcesNeeded();
    }
    
    @Override
    public int compareTo(Request other) {
        if(this.equals(other)){
            return 0;
        }else if(getCostumer().getResourcesNeeded()>other.getCostumer().getResourcesNeeded()){
            return 1;
        }else{
            return -1;
        }
    }

    public String toString(){
        return "REQUEST-> "+
                "FROM" + this.getCostumer().getName()+
                " FOR " + this.getCostumer().getResourcesNeeded() + 
                " RESOURCES";
    }
}
