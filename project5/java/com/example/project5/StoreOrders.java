package com.example.project5;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Stores all orders placed
 * @author Mahad Rauf, Moeez Shahid
 */
public class StoreOrders implements Customizable{
    /** stores all orders */
    private ArrayList<Order> orders;

    /**
     * constructor for StoreOrders that creates the ArrayList to store orders
     */
    public StoreOrders(){
        orders = new ArrayList<Order>();
    }

    /**
     * returns all the orders in the database
     * @return all the orders in the database
     */
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    /**
     * removes parameter order from database
     * @param obj order to remove
     * @return true if object of type Order and is removed, otherwise false
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            Order objO = (Order) obj;
            return orders.remove(objO);
        }else{
            return false;
        }
    }

    /**
     * adds parameter order to database
     * @param obj order to add
     * @return true if object of type Order and is added, otherwise false
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order objO = (Order) obj;
            return orders.add(objO);
        }else{
            return false;
        }
    }

    /**
     * returns String representation o all the Orders in the database
     * @return String representation o all the Orders in the database
     */
    @Override
    public String toString(){
        String ret = "";
        for(Order o : this.orders){
            ret = ret + o.toString() + " \n";
        }
        return ret;
    }

    /**
     * exports the Orders in StoreOrders to a .txt file
     * @param path name of file to export to
     * @return String detailing the outcome of the export
     */
    public String export(String path) {
        try {
            File myObj = new File(path);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(this.toString());
            myWriter.close();
            return "Orders exported to file: orders.txt (data overwritten if file already exists).\n";
        } catch (Exception e) {
            return "An error occurred while exporting the file.\n";
        }
    }

}