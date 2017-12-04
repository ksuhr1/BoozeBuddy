package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class spendingcalculator extends AppCompatActivity {

    TextView result1, result2, result3;
    EditText number1, number2, number3;
    float num1, num2, num3;
    float result_sum1, result_sum2, result_sum3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendingcalculator);
//        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));

        number1 = (EditText)findViewById(R.id.Number1);
        number2 = (EditText)findViewById(R.id.Number2);
        number3 = (EditText)findViewById(R.id.Number3);

        result1 = (TextView)findViewById(R.id.Result1);
        result2 = (TextView)findViewById(R.id.Result2);
        result3 = (TextView)findViewById(R.id.Result3);



        View.OnKeyListener listener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // get what the user has typed

                try {
                    num1 = Integer.parseInt(number1.getText().toString());
                    num2 = Integer.parseInt(number2.getText().toString());
                    num3 = Float.parseFloat(number3.getText().toString());
                } catch (NumberFormatException e) {
                    return false;
                }

                //Calculate automatically
                result_sum1 = num1*num2*num3;

                result_sum2 = result_sum1 * 4;

                result_sum3 = result_sum1 * 52;

                result1.setText(String.valueOf(result_sum1));
                result2.setText(String.valueOf(result_sum2));
                result3.setText(String.valueOf(result_sum3));



                return false;
            }
        };

        number1.setOnKeyListener(listener);
        number2.setOnKeyListener(listener);
        number3.setOnKeyListener(listener);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(spendingcalculator.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hamburger, menu);
        return true;
    }


}
