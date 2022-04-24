package com.example.project5;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

/**
 * main activity for cafe application
 * @author Mahad rauf, Moeez Shahid
 */
public class MainActivity extends AppCompatActivity {
    /** all orders placed */
    public static StoreOrders orders = new StoreOrders();
    /** current order being made */
    public static Order currentOrder = new Order();
    /** total price of all the donuts in the order */
    public static double donutTotal = 0;

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCoffeeListener();
        setDonutListener();
        setOrderListener();
        setAllOrdersListener();
    }

    /**
     * sets an OnClickListener to 'ADD COFFEE' Button
     */
    private void setCoffeeListener(){
        Button button = findViewById(R.id.addCoffee);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * action upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * sets an OnClickListener to 'ADD Donuts' Button
     */
    private void setDonutListener(){
        Button button = findViewById(R.id.addDonut);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * action upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DonutActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * sets an OnClickListener to 'VIEW CURRENT ORDER' Button
     */
    private void setOrderListener(){
        Button button = findViewById(R.id.currentOrder);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * action upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * sets an OnClickListener to 'VIEW STORE ORDERS' Button
     */
    private void setAllOrdersListener(){
        Button button = findViewById(R.id.storeOrders);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * action upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(intent);
            }
        });
    }

}