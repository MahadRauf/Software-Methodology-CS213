package com.project4.group33project4;

import java.util.ArrayList;

public class Order implements Customizable{
    private static int ORDER_NUM = 0;
    private ArrayList<MenuItem> order;
    private int orderNum;

    public Order(){
        order = new ArrayList<MenuItem>();
        this.orderNum = ORDER_NUM;
        ORDER_NUM++;
    }

    public ArrayList<MenuItem> getItems(){
        return this.order;
    }

    public int getOrderNum(){
        return this.orderNum;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem objS = (MenuItem) obj;
            return order.add(objS);
        }else{
            return false;
        }
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem objS = (MenuItem) obj;
            return order.remove(objS);
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Order){
            Order objO = (Order) obj;
            if(objO.orderNum == this.orderNum){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String ret = "Order Number: " + this.orderNum + "\n";
        for(MenuItem i : order){
            ret = ret + i.toString() + "\n";
        }
        return ret;
    }
}
