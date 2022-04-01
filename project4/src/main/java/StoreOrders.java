package com.project4.group33project4;

import java.util.ArrayList;

public class StoreOrders implements Customizable{
    private ArrayList<Order> orders;

    public StoreOrders(){
        orders = new ArrayList<Order>();
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            Order objO = (Order) obj;
            return orders.remove(objO);
        }else{
            return false;
        }
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order objO = (Order) obj;
            return orders.add(objO);
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String ret = "";
        for(Order o : this.orders){
            ret = o.toString() + " \n";
        }
        return ret;
    }
}
