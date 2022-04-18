package com.example.project5;

import java.util.ArrayList;

/**
 * Collection of MenuItems as an Order
 * @author Mahad Rauf, Moeez Shahid
 */
public class Order implements Customizable{
    private static int ORDER_NUM = 0;

    /** all items currently in the order */
    private ArrayList<MenuItem> order;
    /** number order is identified by */
    private int orderNum;
    /** total price of the order */
    private double total;

    /**
     * constrcutor for order that creates the order and unique number to identify it
     */
    public Order(){
        order = new ArrayList<MenuItem>();
        this.orderNum = ORDER_NUM;
        ORDER_NUM++;
    }

    /**
     * returns all the items in the order
     * @return all the items in the order
     */
    public ArrayList<MenuItem> getItems(){
        return this.order;
    }

    /**
     * returns the order number of the order
     * @return the order number of the order
     */
    public int getOrderNum(){
        return this.orderNum;
    }

    /**
     * sets the total price of an order
     * @param amount price amount
     */
    public void setTotal(double amount){
        this.total = amount;
    }

    /**
     * adds an item to the order
     * @param obj item to add
     * @return true if object is of type MenuItem and added, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem objS = (MenuItem) obj;
            return order.add(objS);
        }else{
            return false;
        }
    }

    /**
     * removes an item form the order
     * @param obj item to remove
     * @return true if object is of type MenuItem and removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem objS = (MenuItem) obj;
            return order.remove(objS);
        }else{
            return false;
        }
    }

    /**
     * checks orders for equality by order number
     * @param obj order to compare
     * @return true of object os of type Order and equal, otherwise false
     */
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

    /**
     * returns String representation of order and items within it
     * @return String representation of order and items within it
     */
    @Override
    public String toString(){
        String ret = "Order Number: " + this.orderNum + "\nTotal(w/tax): $"+ String.format("%,.2f", this.total) + "\n";
        for(MenuItem i : order){
            ret = ret + i.toString() + "\n";
        }
        return ret;
    }
}