package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;



public class DonutActivity extends AppCompatActivity {
    private ArrayList<Donut> donuts = new ArrayList<>();
    private Spinner flavors;
    private Spinner nums;
    private EditText subtotal;
    private double currentPrice = 1.59;
    private int amount = 1;
    private int donutType = Donut.YEAST;
    
    /** Base size of the coffee: Short */

    private int [] donutImages = {R.drawable.sugar, R.drawable.glazed, R.drawable.chocolate,
            R.drawable.blueberry, R.drawable.velvet, R.drawable.boston, R.drawable.lemon,
            R.drawable.strawberry, R.drawable.vanilla, R.drawable.birthday, R.drawable.jelly,
            R.drawable.powder};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        DonutAdapter adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        flavors = findViewById(R.id.donut_spinner);
        nums = findViewById(R.id.amount_spinner);
        subtotal = findViewById(R.id.donut_subtotal);
        setFlavorListener();
        setAmountListener();
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] donutNames = getResources().getStringArray(R.array.donutNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < donutNames.length; i++) {
            donuts.add(new Donut(1.39, 1, 1, i+1, donutImages[i]));
        }
    }

    private void setFlavorListener(){
        flavors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String flavor = (String) flavors.getSelectedItem();
                if(flavor.equals("Yeast")){
                    donutType = Donut.YEAST;
                    currentPrice = Donut.YEAST_PRICE * amount;
                }else if(flavor.equals("Cake")){
                    donutType = Donut.CAKE;
                    currentPrice = Donut.CAKE_PRICE * amount;
                }else if(flavor.equals("Hole")){
                    donutType = Donut.HOLE;
                    currentPrice = Donut.HOLE_PRICE * amount;
                }
                setPrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                flavors.setSelection(0);
            }
        });
    }

    private void setAmountListener(){
        nums.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String num = (String) nums.getSelectedItem();
                if(num.equals("1")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }else if(num.equals("2")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }else if(num.equals("3")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }else if(num.equals("4")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }else if(num.equals("5")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }
                else if(num.equals("6")){
                    amount = Integer.parseInt(num);
                    currentPrice = getPrice(donutType) * amount;
                }
                setPrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                flavors.setSelection(0);
            }
        });
    }
    private double getPrice(int type){
        if(type == Donut.YEAST){
            return Donut.YEAST_PRICE;
        }
        else if(type == Donut.CAKE){
            return Donut.CAKE_PRICE;
        }
        else if(type == Donut.HOLE){
            return Donut.HOLE_PRICE;
        }
        return Donut.STRAWBERRY;
    }

    private void setPrice(){
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.setText(amount);
    }
}
