package com.project4.group33project4;

import java.util.HashSet;

public class Coffee extends MenuItem implements Customizable{
    // maybe add a price increase constant that is multiplied by the size
    // so base price and price added per size price = BASE_PRICE + SIZE_CONSTANT*size
    // maybe do that in the controller?
    protected static final double ADD_IN_PRICE = 0.30;
    protected static final int SHORT = 0;
    protected static final int TALL = 1;
    protected static final int GRANDE = 2;
    protected static final int VENTI = 3;
    protected HashSet<String> addIns;
    private int size;

    public Coffee(double price, int quantity, int size){
        super(price, quantity);
        this.size = size;
        addIns = new HashSet<String>();
    }

    @Override
    public double itemPrice() {
        int numAddIns = this.addIns.size();
        double ret = super.itemPrice() + numAddIns * ADD_IN_PRICE;
        return ret;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String objS = (String) obj;
            return addIns.add(objS);
        }else{
            return false;
        }
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String objS = (String) obj;
            return addIns.remove(objS);
        }else{
            return false;
        }
    }

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

    @Override
    public String toString(){
        String ret = this.getSize() + " ";
        if(addIns.size() > 0){
            ret = ret + " ::";
            for(String s : addIns){
                ret = ret + s + "::";
            }
        }
        ret = ret + "(" + getQuantity() + ")";
        return ret;
    }
}
