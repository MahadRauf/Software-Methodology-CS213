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

import java.util.HashSet;


public class CoffeeActivity extends AppCompatActivity {
    private Spinner sizes;
    private EditText subtotal;
    private CheckBox cream;
    private CheckBox milk;
    private CheckBox syrup;
    private CheckBox caramel;
    private CheckBox wCream;
    private Button orderButton;
    /** Base price of Short coffee with no add-ins */
    private double currentPrice = 1.69;
    /** Base size of the coffee: Short */
    private int coffeeSize = Coffee.SHORT;
    /** add-ins ot be added to the coffee */
    private HashSet<String> addIns = new HashSet<String>();

    private static final double BASE_PRICE = 1.69;
    private static final double ADD_IN_PRICE = Coffee.ADD_IN_PRICE;
    private static final double SIZE_INCREASE_PRICE = Coffee.SIZE_INCREASE_PRICE;

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

    private void initBoxes(){
        cream = findViewById(R.id.cream);
        setBoxListener(cream);
        milk = findViewById(R.id.milk);
        setBoxListener(milk);
        syrup = findViewById(R.id.syrup);
        setBoxListener(syrup);
        caramel = findViewById(R.id.caramel);
        setBoxListener(caramel);
        wCream = findViewById(R.id.w_cream);
        setBoxListener(wCream);
    }

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

    private void setSizesListener(){
        sizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                sizes.setSelection(0);
            }
        });
    }

    private void setPrice(){
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.setText(amount);
    }
}