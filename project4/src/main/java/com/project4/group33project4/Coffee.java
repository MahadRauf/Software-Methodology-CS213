package com.project4.group33project4;

import java.util.HashSet;

/**
 * Object representation of coffee with a price, size, and add-ins
 * @author Mahad Rauf, Moeez Shahid
 */
public class Coffee extends MenuItem implements Customizable{
    protected static final double ADD_IN_PRICE = 0.30;
    protected static final double SIZE_INCREASE_PRICE = 0.40;
    protected static final int SHORT = 0;
    protected static final int TALL = 1;
    protected static final int GRANDE = 2;
    protected static final int VENTI = 3;

    /** add-ins of the coffee */
    private HashSet<String> addIns;
    /** size of the coffee */
    private int size;

    /**
     * 2 parameter constructor for coffee
     * @param price price of the coffee
     * @param size size of the coffee
     */
    public Coffee(double price, int size){
        super(price);
        this.size = size;
        addIns = new HashSet<String>();
    }

    /**
     * Takes the object given and adds it to the set of add-ins
     * @param obj add-in to add to add-ins set
     * @return true if object is of type String and is not already in the set, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String objS = (String) obj;
            return addIns.add(objS);
        }else{
            return false;
        }
    }

    /**
     * Takes the object given and removes it from the set of add-ins
     * @param obj add-in to remove to add-ins set
     * @return true if object is of type String and present in the set, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String objS = (String) obj;
            return addIns.remove(objS);
        }else{
            return false;
        }
    }

    /**
     * Checks two coffees for equality of size and add-ins
     * @param obj object to compare to
     * @return true if obj is of type coffee and equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Coffee){
            Coffee ObjC = (Coffee) obj;
            if(ObjC.size == this.size && ObjC.addIns.equals(this.addIns)){
              return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * returns the size of the coffee as a String
     * @return size of the coffee as a String
     */
    public String getSize(){
        if(this.size == SHORT){
            return "Short";
        }else if(this.size == TALL){
            return "Tall";
        }else if(this.size == GRANDE){
            return "Grande";
        }else if(this.size == VENTI){
            return "Venti";
        }
        return "N/A";
    }

    /**
     * returns the coffee as its String representation
     * @return coffee as its String representation
     */
    @Override
    public String toString(){
        String ret = "Coffee: " + this.getSize() + " ";
        if(addIns.size() > 0){
            ret = ret + " ::";
            for(String s : addIns){
                ret = ret + s + "::";
            }
        }
        ret = ret + "(1)";
        return ret;
    }
}
