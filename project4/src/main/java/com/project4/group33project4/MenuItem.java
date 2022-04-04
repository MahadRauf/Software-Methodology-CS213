package com.project4.group33project4;

public abstract class MenuItem {
    private double price;

    public MenuItem(double price){
        this.price = price;
    }

    public double itemPrice(){
        return this.price;
    }
}
