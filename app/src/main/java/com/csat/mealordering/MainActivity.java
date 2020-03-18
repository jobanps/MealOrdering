package com.csat.mealordering;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    ImageView mealImage;
    Spinner mealSpin;
    TextView mealPriceText;
    SeekBar quantitySeekBar;
    TextView quantityText;
    RadioGroup tipRG;
    EditText totalAmount;
    EditText mealAmount;
    EditText taxAmount;
    EditText tipAmount;
    CheckBox checkBox;
    double tipPercentage = 0;
    int quantity = 0;
    double pricePerMeal = 0;
    double total,mealAmt,tax,tip;

    private ArrayList<String> imageidArr = new ArrayList<>();
    private ArrayList<String> mealNameArr = new ArrayList<>();
    private ArrayList<String>  mealPriceArr = new ArrayList<>();
    private ArrayList<String> quantityArr = new ArrayList<>();
    private ArrayList<String> totalPriceArr = new ArrayList<>();
    int imageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalAmount = findViewById(R.id.totalPrice);
        mealAmount = findViewById(R.id.editMealAmount);
        taxAmount = findViewById(R.id.editTaxAmount);
        tipAmount = findViewById(R.id.editTipAmount);

        //For image
        mealImage = findViewById(R.id.mealImage);
        final int[] mealSrcArray = {
                R.drawable.meal0,
                R.drawable.meal1,
                R.drawable.meal2,
                R.drawable.meal3,
                R.drawable.meal4,
                R.drawable.meal5,
                R.drawable.meal6,
                R.drawable.meal7,
                R.drawable.meal8,
                R.drawable.meal9,
                R.drawable.meal10
        };

        //price Array
        final double[] mealPrice = {0.00,12.99,15.99,9.99,14,20.00,18.00,8.00,12,13.99,16.00};
        mealPriceText = findViewById(R.id.mealPrice);


        //Spinner
        mealSpin = findViewById(R.id.spinner);
        mealSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int index = arg0.getSelectedItemPosition();
                imageId = mealSrcArray[index];
                mealImage.setImageResource(imageId);
                pricePerMeal = mealPrice[index];
                mealPriceText.setText("$" + pricePerMeal);
                calculatePrice();
                updatePrice();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //quantity change
        quantityText = findViewById(R.id.quantityText);
        final String quantityStr = quantityText.getText().toString();

        //SeekBar

        quantitySeekBar = findViewById(R.id.quantitySeekBar);
        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){}
                    //seekBar.setProgress(Integer.valueOf(et.getText().toString()));
                    quantity = quantitySeekBar.getProgress();
                    quantityText.setText(quantityStr + " : " + quantity);
                    calculatePrice();
                    updatePrice();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //RadioGroup
        tipRG = findViewById(R.id.tipRadioGroup);
        tipPercentage = 0.10; // default value
        tipRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioBtnId = tipRG.getCheckedRadioButtonId();
                int selectedIndex = tipRG.indexOfChild(findViewById(radioBtnId));

                if(selectedIndex == 0)
                    tipPercentage = 0.10;
                else if(selectedIndex == 1)
                    tipPercentage = 0.15;
                else
                    tipPercentage = 0.20;

                calculatePrice();
                updatePrice();
            }

        });


        checkBox = findViewById(R.id.confirmCheck);

           }

    public void confirmOrder(View view) {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if(mealSpin.getSelectedItemId() != 0) {

            if(quantitySeekBar.getProgress() > 0) {

                if(tip != 0) {

                    if (checkBox.isChecked()) {

                        updateOrderData();

                        updateSharedPreferences();

                        Intent nextActivity = new Intent(this, OrderDetails.class);
                        startActivity(nextActivity);

                    } else {
                        Toast.makeText(context, "Please confirm order", duration).show();
                    }
                } else {
                    Toast.makeText(context,"Please select tip percentage", duration).show();
                }
            } else {
                Toast.makeText(context,"Please select 1 or more quantity", duration).show();
            }


        } else {

            Toast.makeText(context,"Please choose meal", duration).show();

        }

    }

    @Override
    protected void onRestart() {
        initFields();
        super.onRestart();

    }

    public void initFields(){

        totalAmount.setText("0.0");
        taxAmount.setText("0.0");
        tipAmount.setText("0.0");
        mealAmount.setText("0.0");
        quantitySeekBar.setProgress(0);
        checkBox.setChecked(false);
        mealSpin.setSelection(0);
        tipRG.clearCheck();
        tipPercentage = 0;
        quantity = 0;
        pricePerMeal = 0;
        total = 0;
        mealAmt = 0;
        tax = 0;
        tip=0;


    }

    public void updateOrderData() {

        imageidArr.add(String.valueOf(imageId));
        mealNameArr.add(mealSpin.getSelectedItem().toString());
        mealPriceArr.add(String.valueOf(pricePerMeal));
        quantityArr.add(String.valueOf(quantity));
        totalPriceArr.add(String.valueOf(total));
    }
    public void updateSharedPreferences() {

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.csat.mealOrdering", Context.MODE_PRIVATE);
        sharedPreferences.edit().putStringSet("imageidArr", new HashSet<String>(imageidArr)).apply();
        sharedPreferences.edit().putStringSet("mealNameArr", new HashSet<String>(mealNameArr)).apply();
        sharedPreferences.edit().putStringSet("mealPriceArr", new HashSet<String>(mealPriceArr)).apply();
        sharedPreferences.edit().putStringSet("quantityArr", new HashSet<String>(quantityArr)).apply();
        sharedPreferences.edit().putStringSet("totalPriceArr", new HashSet<String>(totalPriceArr)).apply();
    }

    public void updatePrice(){

        totalAmount.setText("$" + String.format("%.2f", total));
        taxAmount.setText("$" + String.format("%.2f", tax));
        tipAmount.setText("$" + String.format("%.2f", tip));
        mealAmount.setText("$" + String.format("%.2f", mealAmt));

    }
    public void calculatePrice(){

        mealAmt = pricePerMeal * quantity;
        tax = mealAmt * 0.13;
        tip = mealAmt * tipPercentage;
        total = mealAmt + tax + tip;

    }
}
