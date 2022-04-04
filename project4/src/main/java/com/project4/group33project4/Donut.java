package com.project4.group33project4;

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
    private int type;
    private int flavor;
    private int quantity;

    public Donut(double price, int quantity, int type, int flavor){
        super(price);
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /*@Override
    public double itemPrice() {
        return super.itemPrice() * this.quantity;
    }*/

   /* public int getType(){
        return this.type;
    }*/

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

    @Override
    public String toString(){
        String ret = this.getType() + " ," + this.getFlavor() + " (" + this.quantity + ")";
        return ret;
    }
}
