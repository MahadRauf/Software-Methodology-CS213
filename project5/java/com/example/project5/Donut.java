package com.example.project5;
import java.io.Serializable;
/**
 * Object representation of donut with a price, flavor, and quantity
 * @author Mahad Rauf, Moeez Shahid
 */
public class Donut extends MenuItem implements Serializable{
    protected static final double YEAST_PRICE = 1.59;
    protected static final double CAKE_PRICE = 1.79;
    protected static final double HOLE_PRICE = 0.39;
    protected static final int YEAST = 0;
    protected static final int CAKE = 1;
    protected static final int HOLE = 2;

    protected static final int SUGAR = 1;
    protected static final int GLAZED = 2;
    protected static final int CHOCOLATE = 3;
    protected static final int BLUEBERRY = 4;
    protected static final int VELVET = 5;
    protected static final int BOSTON = 6;
    protected static final int LEMON = 7;
    protected static final int STRAWBERRY = 8;
    protected static final int VANILLA = 9;
    protected static final int BIRTHDAY = 10;
    protected static final int JELLY = 11;
    protected static final int POWDER = 12;


    /** type of the donut */
    private int type;
    /** flavor of the donut */
    private int flavor;
    /** quantity of the donut */
    private int quantity;

    private int image;

    /**
     * 3 parameter constructor for Donut type
     * @param price donut price
     * @param quantity donut quantity
     * @param type donut type
     * @param flavor donut flavor
     */
    public Donut(double price, int quantity, int type, int flavor, int image){
        super(price);
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
        this.image = image;
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

    public double getTypePrice(){
        if(this.type == YEAST){
            return YEAST_PRICE;
        }else if(this.type == CAKE){
            return CAKE_PRICE;
        }else if(this.type == HOLE){
            return HOLE_PRICE;
        }
        return 0;
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

    public String getFlavor2(){
        switch (this.flavor){
            case SUGAR:
                return "Sugar";

            case GLAZED:
                return "Glazed";

            case CHOCOLATE:
                return "Chocolate";

            case BLUEBERRY:
                return "Blueberry";


            case VELVET:
                return "Red Velvet";

            case BOSTON:
                return "Boston Kreme";
            case LEMON:
                return "Lemon";

            case STRAWBERRY:
                return "Strawberry";


            case VANILLA:
                return "Vanilla";

            case BIRTHDAY:
                return "Birthday";

            case JELLY:
                return "Jelly";

            case POWDER:
                return "Powder";
        }
        return ("N/A");
    }

    public int getImage() {
        return image;
    }

    
    @Override
    public String toString(){
        String ret = "Donut: " + this.getType() + ", " + this.getFlavor2() + " (" + this.quantity + ")";
        return ret;
    }
}
