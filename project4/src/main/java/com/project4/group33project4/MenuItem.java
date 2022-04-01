package com.project4.group33project4;

public abstract class MenuItem {
    private double price;
    private int quantity;

    public MenuItem(double price, int quantity){
        this.price = price;
        this.quantity = quantity;
    }

    public double itemPrice(){
        return this.price * this.quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
