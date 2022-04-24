package com.example.project5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Activity for selecting donut flavor view
 * @author Mahad Rauf, Moeez Shahid
 */
public class DonutActivity extends AppCompatActivity {
    /** ArrayList of all the donut flavors */
    private ArrayList<Donut> donuts = new ArrayList<>();
    /** integer array with all the donut images' index */
    private int [] donutImages = {R.drawable.sugar, R.drawable.glazed, R.drawable.chocolate,
            R.drawable.blueberry, R.drawable.velvet, R.drawable.boston, R.drawable.lemon,
            R.drawable.strawberry, R.drawable.vanilla, R.drawable.birthday, R.drawable.jelly,
            R.drawable.powder};

    /**
     * Begins and initializes the Activity
     * @param savedInstanceState state of application in prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        DonutAdapter adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
        TextView total = findViewById(R.id.donut_all_price);
        String totalText = "$" + String.format("%,.2f", MainActivity.donutTotal);
        total.setText(totalText);
    }

    /**
     * sets the subtotal TextView upon the Activity resuming
     */
    @Override
    protected void onResume() {
        super.onResume();
        TextView total = findViewById(R.id.donut_all_price);
        String totalText = "$" + String.format("%,.2f", MainActivity.donutTotal);
        total.setText(totalText);
    }

    /**
     * adds all the donuts with corresponding images to donuts ArrayList
     */
    private void setupMenuItems() {
        String [] donutNames = getResources().getStringArray(R.array.donutNames);
        for (int i = 0; i < donutNames.length; i++) {
            donuts.add(new Donut(Donut.YEAST_PRICE, 1, Donut.CAKE, i+1, donutImages[i]));
        }
    }
}