package com.example.project5;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static StoreOrders orders = new StoreOrders();
    public static Order currentOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Switches to addCoffeeActivity. Linked to button onClick.
     *
     * @param view
     */
    public void addCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Switches to addDonutActivity. Linked to button onClick.
     *
     * @param view
     */
    public void addDonut(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }
    /**
     * Switches to CurrentOrderActivity. Linked to button onClick.
     *
     * @param view
     */
    public void viewCurrentOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * Switches to StoreOrdersActivity. Linked to button onClick.
     *
     * @param view
     */
    public void viewStoreOrders(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}