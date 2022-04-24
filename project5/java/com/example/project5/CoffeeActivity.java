package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

/**
 * Activity for ordering coffee view
 * @author Mahad Rauf, Moeez Shahid
 */
public class CoffeeActivity extends AppCompatActivity {
    /** sizes spinner in the Activity */
    private Spinner sizes;
    /** subtotal amount TextView in the Activity */
    private TextView subtotal;
    /** 'ADD TO ORDER' Button in the Activity */
    private Button orderButton;
    /** Base price of Short coffee with no add-ins */
    private double currentPrice = BASE_PRICE;
    /** Base size of the coffee: Short */
    private int coffeeSize = Coffee.SHORT;
    /** add-ins to be added to the coffee */
    private HashSet<String> addIns = new HashSet<String>();

    private static final double BASE_PRICE = 1.69;
    private static final double ADD_IN_PRICE = Coffee.ADD_IN_PRICE;
    private static final double SIZE_INCREASE_PRICE = Coffee.SIZE_INCREASE_PRICE;

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        sizes = findViewById(R.id.coffee_spinner);
        subtotal = findViewById(R.id.coffee_subtotal);
        setSizesListener();
        initBoxes();
        orderButton = findViewById(R.id.coffee_add);
        setOrderListener();
    }

    /**
     * sets an OnClickListener to the order button in the Activity
     */
    private void setOrderListener(){
        orderButton.setOnClickListener(view -> {
            Coffee toAdd = new Coffee(currentPrice, coffeeSize);
            for(String s : addIns){
                toAdd.add(s);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(CoffeeActivity.this);
            builder.setMessage("Add " + toAdd.toString() + " to Order?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        MainActivity.currentOrder.add(toAdd);
                        Toast.makeText(CoffeeActivity.this, "Coffee Added to Order", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        Toast.makeText(CoffeeActivity.this, "Coffee Not Added to Order", Toast.LENGTH_SHORT).show();
                    });
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    /**
     * sets OnCheckChangedListener to all the CheckBoxes for add-ins
     */
    private void initBoxes(){
        CheckBox cream = findViewById(R.id.cream);
        setBoxListener(cream);
        CheckBox milk = findViewById(R.id.milk);
        setBoxListener(milk);
        CheckBox syrup = findViewById(R.id.syrup);
        setBoxListener(syrup);
        CheckBox caramel = findViewById(R.id.caramel);
        setBoxListener(caramel);
        CheckBox wCream = findViewById(R.id.w_cream);
        setBoxListener(wCream);
    }

    /**
     * sets an onCheckChangedListener to the specified CheckBox
     * @param cBox CheckBox to add the onCheckChangedListener to
     */
    private void setBoxListener(CheckBox cBox){
        cBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                addIns.add(cBox.getText().toString());
                currentPrice += ADD_IN_PRICE;
            }else{
                addIns.remove(cBox.getText().toString());
                currentPrice -= ADD_IN_PRICE;
            }
            setPrice();
        });
    }

    /**
     * sets an OnItemSelectedListener to sizes spinner
     */
    private void setSizesListener(){
        sizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * action when something is selected
             * @param parentView AdapterView of the spinner
             * @param selectedItemView selected item of the spinner
             * @param position position of the selected item
             * @param id id of the item
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String size = (String) sizes.getSelectedItem();
                if(size.equals("Short")){
                    coffeeSize = Coffee.SHORT;
                    currentPrice = BASE_PRICE + addIns.size() * ADD_IN_PRICE;
                }else if(size.equals("Tall")){
                    coffeeSize = Coffee.TALL;
                    currentPrice = BASE_PRICE + Coffee.TALL * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
                }else if(size.equals("Grande")){
                    coffeeSize = Coffee.GRANDE;
                    currentPrice = BASE_PRICE + Coffee.GRANDE * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
                }else if(size.equals("Venti")){
                    coffeeSize = Coffee.VENTI;
                    currentPrice = BASE_PRICE + Coffee.VENTI * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
                }
                setPrice();
            }
            /**
             * action when nothing is selected
             * @param parentView AdapterView of the spinner
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                sizes.setSelection(0);
            }
        });
    }

    /**
     * sets the amount of the subtotal TextField
     */
    private void setPrice(){
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.setText(amount);
    }
}