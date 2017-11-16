package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BACCalculator extends AppCompatActivity {

    private Button calculate;
    private EditText weight, drinks, time;
    private Spinner gender;
    private TextView BAC;

    private double weightDouble, drinkDouble, timeDouble, genderConstant, BACDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccalculator);

        calculate = (Button) findViewById(R.id.calculate);
        weight = (EditText) findViewById(R.id.weight);
        drinks = (EditText) findViewById(R.id.standardDrinks);
        time = (EditText) findViewById(R.id.hours);
        gender = (Spinner) findViewById(R.id.gender);
        BAC = (TextView) findViewById(R.id.BAC);
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        weight.setText(tracker.getString("weight", null));


        String[] genderArray = new String[]{
                "Male", "Female"
        };

        gender.setAdapter(new ArrayAdapter<String>(BACCalculator.this, android.R.layout.simple_spinner_dropdown_item, genderArray));
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    genderConstant = 0.73;
                } else {
                    genderConstant = 0.66;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weight.getText().toString().equals("") | drinks.getText().toString().equals("") | time.getText().toString().equals("")) {
                    Toast.makeText(BACCalculator.this, "Cannot leave fields blank", Toast.LENGTH_SHORT).show();
                } else {
                    weightDouble = Double.parseDouble(weight.getText().toString());
                    drinkDouble = Double.parseDouble(drinks.getText().toString());
                    timeDouble = Double.parseDouble(time.getText().toString());

                    BACDouble = (((drinkDouble * 12 * 0.05) * 5.14) / (weightDouble * genderConstant)) - (0.015 * timeDouble);
                    DecimalFormat newDouble = new DecimalFormat("#.###");
                    if(BACDouble> .08){
                        Toast.makeText(BACCalculator.this, "You are over the legal limit", Toast.LENGTH_SHORT).show();
                    }
                    BAC.setText(newDouble.format(BACDouble) + " % BAC");
                }
            }
        });


    }
}
