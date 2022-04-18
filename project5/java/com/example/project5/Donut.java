package com.example.project5;
/**
 * Object representation of donut with a price, flavor, and quantity
 * @author Mahad Rauf, Moeez Shahid
 */
public class Donut extends MenuItem{
    protected static final double YEAST_PRICE = 1.59;
    protected static final double CAKE_PRICE = 1.79;
    protected static final double HOLE_PRICE = 0.39;
    protected static final int YEAST = 0;
    protected static final int CAKE = 1;
    protected static final int HOLE = 2;
    protected static final int SUGAR = 3;
    protected static final int GLAZED = 4;
    protected static final int CHOCOLATE = 5;

    /** type of the donut */
    private int type;
    /** flavor of the donut */
    private int flavor;
    /** quantity of the donut */
    private int quantity;

    /**
     * 3 parameter constructor for Donut type
     * @param price donut price
     * @param quantity donut quantity
     * @param type donut type
     * @param flavor donut flavor
     */
    public Donut(double price, int quantity, int type, int flavor){
        super(price);
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Checks if two donuts are equal based on type, flavor, and quantity
     * @param obj object to compare for equality
     * @return true if of type Donut and equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Donut){
            Donut ObjD = (Donut) obj;
            if(ObjD.type == this.type && ObjD.flavor == this.flavor && ObjD.quantity == this.quantity){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * returns the type of the donut as a String
     * @return donut type as a String
     */
    private String getType(){
        if(this.type == YEAST){
            return "YEAST";
        }else if(this.type == CAKE){
            return "CAKE";
        }else if(this.type == HOLE){
            return "HOLE";
        }
        return "N/A";
    }

    /**
     * returns donut flavor as a String
     * @return donut flavor as a String
     */
    private String getFlavor(){
        if(this.flavor == SUGAR){
            return "SUGAR";
        }else if(this.flavor == GLAZED){
            return "GLAZED";
        }else if(this.flavor == CHOCOLATE){
            return "CHOCOLATE";
        }
        return "N/A";
    }

    /**
     * returns the String representation of the donut
     * @return String representation of the donut
     */
    @Override
    public String toString(){
        String ret = "Donut: " + this.getType() + ", " + this.getFlavor() + " (" + this.quantity + ")";
        return ret;
    }
}