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

public class OrderActivity extends AppCompatActivity {
    private ListView order;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private Button placeOrder;

    private static final double SALES_TAX = 0.06625;

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
        placeOrder = findViewById(R.id.order_place);
        setButtonListener(placeOrder);
    }

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
                        finish();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        Toast.makeText(OrderActivity.this, "Order Not Placed", Toast.LENGTH_SHORT).show();
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    private void setValues(){
        double subTotl = 0;
        for(MenuItem i : MainActivity.currentOrder.getItems()){
            subTotl += i.itemPrice();
        }
        double sTax = subTotl * SALES_TAX;
        double totl = subTotl + sTax;
        String subtotalText = "$" + String.format("%,.2f", subTotl);
        String taxText = "$" + String.format("%,.2f", sTax);
        String totalText = "$" + String.format("%,.2f", totl);
        subtotal.setText(subtotalText);
        tax.setText(taxText);
        total.setText(totalText);
    }

    private void initList(){
        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1,
                MainActivity.currentOrder.getItems());
        order.setAdapter(arrayAdapter);
        order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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