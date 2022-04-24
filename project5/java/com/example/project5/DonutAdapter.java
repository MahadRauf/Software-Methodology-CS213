package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class to be used to instantiate an adapter for the RecyclerView.
 * @author Mahad Rauf, Moeez Shahid
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder>{
    /** the context to inflate the layout */
    private Context context;
    /** the data binding to each row of RecyclerView */
    private ArrayList<Donut> donuts;

    /**
     * constructor for DonutAdapter
     * @param context the context to inflate the layout
     * @param donuts the data binding to each row of RecyclerView
     */
    public DonutAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method will inflate the row layout for the donut in the RecyclerView
     * @param parent parent View that the RecyclerView is part of
     * @param viewType the type of the view
     * @return the DonutHolder for the RecyclerView
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new DonutHolder(view);
    }

    /**
     * Assign data values for each row according to when the donut becomes
     * visible on the screen.
     * @param holder the instance of DonutHolder
     * @param position the index of the donut in the list of donut
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.tv_name.setText(donuts.get(position).getFlavor());
        holder.im_donut.setImageResource(donuts.get(position).getImage());
    }

    /**
     * Get the number of donuts in the ArrayList.
     * @return the number of donuts in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }


    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        /** TextView in row layout */
        private TextView tv_name;
        /** ImageView in row layout */
        private ImageView im_donut;
        /** The row layout */
        private ConstraintLayout parentLayout;

        /**
         * constructor for the DonutHolder of the RecyclerView
         * @param donutView view where the recyclerView is
         */
        public DonutHolder(@NonNull View donutView) {
            super(donutView);
            tv_name = donutView.findViewById(R.id.tv_flavor);
            im_donut = donutView.findViewById(R.id.im_donut);
            parentLayout = donutView.findViewById(R.id.rowLayout);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                /**
                 * onClick for each row
                 * @param view the row of the RecyclerView
                 */
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(donutView.getContext(), DonutOrderActivity.class);
                    intent.putExtra("ITEM", tv_name.getText().toString());
                    donutView.getContext().startActivity(intent);
                }
            });
        }

    }
}