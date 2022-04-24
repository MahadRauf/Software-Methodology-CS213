package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity for viewing all store orders
 * @author Mahad Rauf, Moeez Shahid
 */
public class StoreOrdersActivity extends AppCompatActivity {

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        ListView orders = findViewById(R.id.all_listview);
        initList(orders);
    }

    /**
     * initializes the orders ListView with an Adapter and adds an OnItemClickListener to the list items
     * @param list ListView to initialize
     */
    private void initList(ListView list){
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1,
                MainActivity.orders.getOrders());
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * action when something is selected
             * @param adapterView AdapterView of the ListView
             * @param view selected item of the ListView
             * @param i position of the selected item
             * @param l id of the item
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order toRemove = (Order) list.getItemAtPosition(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreOrdersActivity.this);
                builder.setMessage("Remove Order Number: " + toRemove.getOrderNum() + " from Order?")
                        .setPositiveButton("Yes", (dialogInterface, j) -> {
                            MainActivity.orders.remove(toRemove);
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(StoreOrdersActivity.this, "Order Removed", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", (dialogInterface, j) -> {
                            Toast.makeText(StoreOrdersActivity.this, "Order Not Removed", Toast.LENGTH_SHORT).show();
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}