package com.example.project5;

/**
 * abstract class of a MenuItem
 * @author Mahad Rauf, Moeez Shahid
 */
public abstract class MenuItem {
    /** price of the item */
    private double price;

    /**
     * constructor for type MenuItem
     * @param price price of the item
     */
    public MenuItem(double price){
        this.price = price;
    }

    /**
     * returns the price of the item
     * @return price of the item
     */
    public double itemPrice(){
        return this.price;
    }
}