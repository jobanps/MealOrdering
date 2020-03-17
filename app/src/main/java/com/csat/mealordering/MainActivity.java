package com.csat.mealordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    double tipPercentage = 0;
    int quantity = 0;
    double pricePerMeal = 0;
    double total,mealAmt,tax,tip;
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
                mealImage.setImageResource(mealSrcArray[index]);
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
        tipPercentage = 0.10;
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


           }

    public void confirmOrder(View view) {



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
