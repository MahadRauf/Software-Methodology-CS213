package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class DonutHolder (a static class)
 * The DonutHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Donut> donuts; //need the data binding to each row of RecyclerView

    public DonutAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method will inflate the row layout for the donut in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the donut
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new DonutHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the donut becomes
     * visible on the screen.
     * @param holder the instance of DonutHolder
     * @param position the index of the donut in the list of donut
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(donuts.get(position).getFlavor2());
        String donutTypePrice = "$" + String.format("%,.2f", donuts.get(position).getTypePrice());
        //holder.tv_price.setText(donutTypePrice);
        holder.im_donut.setImageResource(donuts.get(position).getImage());
    }

    /**
     * Get the number of donut in the ArrayList.
     * @return the number of donut in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size(); //number of donuts in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView im_donut;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout

        public DonutHolder(@NonNull View donutView) {
            super(donutView);
            tv_name = donutView.findViewById(R.id.tv_flavor);
            //tv_price = donutView.findViewById(R.id.tv_price);
            im_donut = donutView.findViewById(R.id.im_donut);
            btn_add = donutView.findViewById(R.id.btn_add);
            parentLayout = donutView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(donutView); //register the onClicklistener for the button on each row.

            /* set onClickListener for the row layout,
             * clicking on a row will navigate to another Activity
             */
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(donutView.getContext(), DonutActivity.class);
                    intent.putExtra("ITEM", tv_name.getText());
                    donutView.getContext().startActivity(intent);
                }
            });
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param donutView
         */
        private void setAddButtonOnClick(@NonNull View donutView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(donutView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(tv_name.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(donutView.getContext(),
                                    tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(donutView.getContext(),
                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }
}

