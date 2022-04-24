package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity for ordering donut flavor view
 * @author Mahad Rauf, Moeez Shahid
 */
public class DonutOrderActivity extends AppCompatActivity {
    /** subtotal amount TextView in the Activity */
    private TextView priceText;
    /** total of all donuts amount TextView in the Activity */
    private TextView allPriceText;
    /** price of the selected donut */
    private double price = Donut.YEAST_PRICE;
    /** quantity of the donut */
    private int quantity = 1;
    /** selected donut type */
    private int type = Donut.YEAST;
    /** selected donut flavor */
    private int selectedFlavor;

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        Intent intent = getIntent();
        selectedFlavor = getFlavor(intent);
        String item = intent.getStringExtra("ITEM") + " Donut";
        TextView flavor = findViewById(R.id.DO_flavor);
        flavor.setText(item);
        Spinner types = findViewById(R.id.DO_types);
        setTypeLister(types);
        Spinner amount = findViewById(R.id.DO_amount);
        setAmountListener(amount);
        priceText = findViewById(R.id.DO_item_price);
        allPriceText = findViewById(R.id.DO_all_price);
        setAddListener();
        setRemoveListener();
        setPrice();
    }

    /**
     * sets an OnClickLister to the 'ADD TO ORDER' BUTTON
     */
    private void setAddListener(){
        Button add = findViewById(R.id.DO_add);
        add.setOnClickListener(new View.OnClickListener(){
            /**
             * action done upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Donut toAdd = new Donut(price, quantity, type, selectedFlavor, 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(DonutOrderActivity.this);
                builder.setMessage("Add " + toAdd.toString() + " to Order?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            MainActivity.donutTotal += price;
                            MainActivity.currentOrder.add(toAdd);
                            setPrice();
                            Toast.makeText(DonutOrderActivity.this, "Donut(s) Added to Order", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            Toast.makeText(DonutOrderActivity.this, "Donut(s) Not Added to Order", Toast.LENGTH_SHORT).show();
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    /**
     * sets an OnClickLister to the 'REMOVE FROM ORDER' BUTTON
     */
    private void setRemoveListener(){
        Button remove = findViewById(R.id.DO_remove);
        remove.setOnClickListener( new View.OnClickListener() {
            /**
             * action done upon click
             * @param view view clicked
             */
            @Override
            public void onClick(View view) {
                Donut toRemove = new Donut(price, quantity, type, selectedFlavor, 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(DonutOrderActivity.this);
                builder.setMessage("Remove " + toRemove.toString() + " from Order?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            if(MainActivity.currentOrder.remove(toRemove)){
                                MainActivity.donutTotal -= price;
                                setPrice();
                                Toast.makeText(DonutOrderActivity.this, "Donut(s) Removed from Order", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DonutOrderActivity.this, "No Such Donut(s) in Order", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {
                            Toast.makeText(DonutOrderActivity.this, "Donut(s) Not Removed from Order", Toast.LENGTH_SHORT).show();
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    /**
     * sets an OnItemSelectedListener to donut types spinner
     * @param types spinner to add listener
     */
    public void setTypeLister(Spinner types){
        types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * action when something is selected
             * @param adapterView view with the spinner
             * @param view selected item of the spinner
             * @param i position of the selected item
             * @param l id of the item
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) types.getSelectedItem();
                if(selected.equals("Yeast")){
                    type = Donut.YEAST;
                    price = Donut.YEAST_PRICE * quantity;
                }else if(selected.equals("Cake")){
                    type = Donut.CAKE;
                    price = Donut.CAKE_PRICE * quantity;
                }else if(selected.equals("Hole")){
                    type = Donut.HOLE;
                    price = Donut.HOLE_PRICE * quantity;
                }
                setPrice();
            }
            /**
             * action when nothing is selected
             * @param adapterView AdapterView of the spinner
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                types.setSelection(0);
            }
        });
    }

    /**
     * sets the subtotal and all donut pierce TextViews
     */
    private void setPrice(){
        String toSetPrice = "$" + String.format("%,.2f", price);
        priceText.setText(toSetPrice);
        String toSetTotalPrice = "$" + String.format("%,.2f", MainActivity.donutTotal);
        allPriceText.setText(toSetTotalPrice);
    }

    /**
     * sets an OnItemSelectedListener to donut amounts spinner
     * @param amount spinner to add listener
     */
    public void setAmountListener(Spinner amount){
        amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * action when something is selected
             * @param adapterView view with the spinner
             * @param view selected item of the spinner
             * @param i position of the selected item
             * @param l id of the item
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = (String) amount.getSelectedItem();
                quantity = Integer.parseInt(num);
                Donut tempDonut = new Donut(price, quantity, type, selectedFlavor, 0);
                double donutPrice = tempDonut.getTypePrice();
                price = donutPrice * quantity;
                setPrice();
            }
            /**
             * action when nothing is selected
             * @param adapterView AdapterView of the spinner
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                amount.setSelection(0);
            }
        });
    }

    /**
     * gets the flavor selected in the previous Activity
     * @param intent intent form the previous Activity
     * @return integer corresponding to the flavor selected
     */
    public int getFlavor(Intent intent){
        String donutFlavor = intent.getStringExtra("ITEM");
        switch (donutFlavor) {
            case "Sugar":
                return Donut.SUGAR;
            case "Glazed":
                return Donut.GLAZED;
            case "Chocolate":
                return Donut.CHOCOLATE;
            case "Blueberry":
                return Donut.BLUEBERRY;
            case "Red Velvet":
                return Donut.VELVET;
            case "Boston Kreme":
                return Donut.BOSTON;
            case "Lemon":
                return Donut.LEMON;
            case "Strawberry":
                return Donut.STRAWBERRY;
            case "Vanilla":
                return Donut.VANILLA;
            case "Birthday":
                return Donut.BIRTHDAY;
            case "Jelly":
                return Donut.JELLY;
            case "Powder":
                return Donut.POWDER;
        }
        return -1;
    }
}