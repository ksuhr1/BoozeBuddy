
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
import android.widget.TextView;
import android.widget.Toast;

public class partymode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences shottracker = getSharedPreferences("shotbutton", Context.MODE_PRIVATE);
        final SharedPreferences winetracker = getSharedPreferences("winebutton", Context.MODE_PRIVATE);
        final SharedPreferences beertracker = getSharedPreferences("beerbutton", Context.MODE_PRIVATE);
        final SharedPreferences solotracker = getSharedPreferences("solobutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor shoteditor = shottracker.edit();
        final SharedPreferences.Editor wineeditor = winetracker.edit();
        final SharedPreferences.Editor beereditor = beertracker.edit();
        final SharedPreferences.Editor soloeditor = solotracker.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partymode);
        shoteditor.putInt("shotnumber", 0);
        wineeditor.putInt("winenumber", 0);
        beereditor.putInt("beernumber", 0);
        soloeditor.putInt("solonumber", 0);
        shoteditor.commit();
        wineeditor.commit();
        beereditor.commit();
        soloeditor.commit();
    }

    public void shotbutton(View view) {
        final SharedPreferences shottracker = getSharedPreferences("shotbutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor shoteditor = shottracker.edit();
        int value = (shottracker.getInt("shotnumber",0) + 1);
        shoteditor.putInt("shotnumber", value);
        shoteditor.commit();
        Toast.makeText(partymode.this, Integer.toString(shottracker.getInt("shotnumber", 0)), Toast.LENGTH_SHORT).show();
        calculateBAC();

    }

    public void winebutton(View view){
        final SharedPreferences winetracker = getSharedPreferences("winebutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor wineeditor = winetracker.edit();
        int value = winetracker.getInt("winenumber",0) + 1;
        wineeditor.putInt("winenumber", value);
        wineeditor.commit();
        Toast.makeText(partymode.this, Integer.toString(winetracker.getInt("winenumber", 0)), Toast.LENGTH_SHORT).show();
        calculateBAC();
    }

    public void beerbutton(View view){
        final SharedPreferences beertracker = getSharedPreferences("beerbutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor beereditor = beertracker.edit();
        int value = beertracker.getInt("beernumber",0) + 1;
        beereditor.putInt("beernumber", value);
        beereditor.commit();
        Toast.makeText(partymode.this, Integer.toString(beertracker.getInt("beernumber", 0)), Toast.LENGTH_SHORT).show();
        calculateBAC();
    }

    public void solobutton(View view){
        final SharedPreferences solotracker = getSharedPreferences("solobutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor soloeditor = solotracker.edit();
        int value = solotracker.getInt("solonumber",0) + 1;
        soloeditor.putInt("solonumber", value);
        soloeditor.commit();
        Toast.makeText(partymode.this, Integer.toString(solotracker.getInt("solonumber", 0)), Toast.LENGTH_SHORT).show();
        calculateBAC();
    }

    public void calculateBAC(){
        TextView BACshow = (TextView)findViewById(R.id.BACshow);
        final SharedPreferences shottracker = getSharedPreferences("shotbutton", Context.MODE_PRIVATE);
        final SharedPreferences winetracker = getSharedPreferences("winebutton", Context.MODE_PRIVATE);
        final SharedPreferences beertracker = getSharedPreferences("beerbutton", Context.MODE_PRIVATE);
        final SharedPreferences solotracker = getSharedPreferences("solobutton", Context.MODE_PRIVATE);
        double weightDouble, drinkDouble, timeDouble, BACDouble;
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        int drinknumber = 0;
        drinknumber = drinknumber + shottracker.getInt("shotnumber", 0) + winetracker.getInt("winenumber", 0)
                + beertracker.getInt("beernumber", 0) + solotracker.getInt("solonumber", 0);
        weightDouble = Double.parseDouble(tracker.getString("weight", null).toString());
        drinkDouble = drinknumber;
        timeDouble = 1;

        BACDouble = (((drinkDouble * 12 * 0.05) * 5.14) / (weightDouble * .73)) - (0.015 * timeDouble);
        DecimalFormat newDouble = new DecimalFormat("#.###");
        BACshow.setText(newDouble.format(BACDouble) + " % BAC");

    }




}

