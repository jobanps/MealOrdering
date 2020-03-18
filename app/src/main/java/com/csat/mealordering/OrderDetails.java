package com.csat.mealordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        SharedPreferences sharedPreferences = getSharedPreferences("com.csat.mealOrdering", Context.MODE_PRIVATE);
        String[] imageidArr = sharedPreferences.getStringSet("imageidArr", new HashSet<String>()).toArray(new String[0]);
        String[] mealNameArr = sharedPreferences.getStringSet("mealNameArr", new HashSet<String>()).toArray(new String[0]);
        String[] mealPriceArr = sharedPreferences.getStringSet("mealPriceArr", new HashSet<String>()).toArray(new String[0]);
        String[] quantityArr = sharedPreferences.getStringSet("quantityArr", new HashSet<String>()).toArray(new String[0]);
        String[] totalPriceArr = sharedPreferences.getStringSet("totalPriceArr", new HashSet<String>()).toArray(new String[0]);


        ListView listView = findViewById(R.id.orderList);

        List<Meal> orderList = new ArrayList<>();

        for (int i = 0; i < imageidArr.length ; i++) {
            Meal meal = new Meal(Integer.valueOf(imageidArr[i]), mealNameArr[i],Double.valueOf(mealPriceArr[i]),Integer.valueOf(quantityArr[i]),Double.valueOf(totalPriceArr[i]));
            orderList.add(meal);
        }

        OrderAdapter personAdapter = new OrderAdapter(this, R.layout.order_layout, orderList);
        listView.setAdapter(personAdapter);


    }
}
