package com.project4.group33project4;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class StoreOrders implements Customizable{
    private ArrayList<Order> orders;

    public StoreOrders(){
        orders = new ArrayList<Order>();
    }
    public ArrayList<Order> getOrders(){
        return this.orders;
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
    public void export(String path) {
        try {
            File myObj = new File(path);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(this.toString());
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred while exporting the file.");
        }
    }

}
