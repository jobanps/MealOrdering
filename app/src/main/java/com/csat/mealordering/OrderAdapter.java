package com.csat.mealordering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrderAdapter extends ArrayAdapter {

    private List<Meal> orders;
    private final LayoutInflater layoutInflater;
    private final int layoutResource;


    public OrderAdapter(@NonNull Context context, int resource, List<Meal> orders) {
        super(context, resource);
        this.orders = orders;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutResource = resource;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null)
            v = layoutInflater.inflate(layoutResource, parent, false);
        ImageView imageMeal = v.findViewById(R.id.imageView);
        TextView nameText = v.findViewById(R.id.name);
        TextView perMeal = v.findViewById(R.id.perMeal);
        TextView quanText = v.findViewById(R.id.quantity);
        TextView totalText = v.findViewById(R.id.total);

        System.out.println(orders.get(position).mealName);
        imageMeal.setImageResource(orders.get(position).imageid);
        nameText.setText(orders.get(position).mealName);
        perMeal.setText("Meal Price : " + orders.get(position).mealPrice);
        quanText.setText("Quantity : " + orders.get(position).quantity);
        totalText.setText("Total Amount : " + orders.get(position).totalPrice);

        return v;
    }
}
