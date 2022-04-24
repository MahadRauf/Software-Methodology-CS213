package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity for viewing current order
 * @author Mahad Rauf, Moeez Shahid
 */
public class OrderActivity extends AppCompatActivity {
    /** ListView showing items in current order */
    private ListView order;
    /** TextView with subtotal amount of current order */
    private TextView subtotal;
    /** TextView with sales tax amount of current order */
    private TextView tax;
    /** TextView with total amount of current order */
    private TextView total;

    private static final double SALES_TAX = 0.06625;

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order = findViewById(R.id.order_listview);
        initList();
        subtotal = findViewById(R.id.order_subtotal);
        tax = findViewById(R.id.order_tax);
        total = findViewById(R.id.order_total);
        setValues();
        Button placeOrder = findViewById(R.id.order_place);
        setButtonListener(placeOrder);
    }

    /**
     * sets an OnClickListener to the 'PLACE ORDER' Button in the Activity
     */
    private void setButtonListener(Button button){
        button.setOnClickListener(view -> {
            if(MainActivity.currentOrder.getItems().size() == 0){
                Toast.makeText(OrderActivity.this, "Order Not Placed: No Items in Order", Toast.LENGTH_SHORT).show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
            builder.setMessage("Place Order?\nUpon selecting YES, your order will be placed and you will be returned to the previous screen.")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        MainActivity.orders.add(MainActivity.currentOrder);
                        MainActivity.currentOrder = new Order();
                        MainActivity.donutTotal = 0;
                        Toast.makeText(OrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        Toast.makeText(OrderActivity.this, "Order Not Placed", Toast.LENGTH_SHORT).show();
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    /**
     * sets values of the subtotal, tax, and total TextViews
     */
    private void setValues(){
        double subTotl = 0;
        for(MenuItem i : MainActivity.currentOrder.getItems()){
            subTotl += i.itemPrice();
        }
        double sTax = subTotl * SALES_TAX;
        double totl = subTotl + sTax;
        MainActivity.currentOrder.setTotal(totl);
        String subtotalText = "$" + String.format("%,.2f", subTotl);
        String taxText = "$" + String.format("%,.2f", sTax);
        String totalText = "$" + String.format("%,.2f", totl);
        subtotal.setText(subtotalText);
        tax.setText(taxText);
        total.setText(totalText);
    }

    /**
     * initializes the order ListView with an Adapter and adds an OnItemClickListener to the list items
     */
    private void initList(){
        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentOrder.getItems());
        order.setAdapter(arrayAdapter);
        order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * action when something is selected
             * @param adapterView AdapterView of the ListView
             * @param view selected item of the ListView
             * @param i position of the selected item
             * @param l id of the item
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MenuItem toRemove = (MenuItem) order.getItemAtPosition(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage("Remove " + toRemove.toString() + " from Order?")
                        .setPositiveButton("Yes", (dialogInterface, j) -> {
                            MainActivity.currentOrder.remove(toRemove);
                            arrayAdapter.notifyDataSetChanged();
                            setValues();
                            Toast.makeText(OrderActivity.this, "Item Removed from Order", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", (dialogInterface, j) -> {
                            Toast.makeText(OrderActivity.this, "Item Not Removed from Order", Toast.LENGTH_SHORT).show();
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}