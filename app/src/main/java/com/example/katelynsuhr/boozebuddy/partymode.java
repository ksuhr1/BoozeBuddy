
package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class partymode extends AppCompatActivity {

    TextView timer ;
    Button start; ;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;


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
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        shoteditor.putInt("shotnumber", 0);
        wineeditor.putInt("winenumber", 0);
        beereditor.putInt("beernumber", 0);
        soloeditor.putInt("solonumber", 0);
        shoteditor.commit();
        wineeditor.commit();
        beereditor.commit();
        soloeditor.commit();
        timer = (TextView)findViewById(R.id.timer);
        start = (Button)findViewById(R.id.startparty);
        handler = new Handler() ;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(partymode.this, MainActivity.class);
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

    public void shotbutton(View view) {
        final SharedPreferences shottracker = getSharedPreferences("shotbutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor shoteditor = shottracker.edit();
        int value = (shottracker.getInt("shotnumber",0) + 1);
        shoteditor.putInt("shotnumber", value);
        shoteditor.commit();
        calculateBAC();

    }

    public void winebutton(View view){
        final SharedPreferences winetracker = getSharedPreferences("winebutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor wineeditor = winetracker.edit();
        int value = winetracker.getInt("winenumber",0) + 1;
        wineeditor.putInt("winenumber", value);
        wineeditor.commit();
        calculateBAC();
    }

    public void beerbutton(View view){
        final SharedPreferences beertracker = getSharedPreferences("beerbutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor beereditor = beertracker.edit();
        int value = beertracker.getInt("beernumber",0) + 1;
        beereditor.putInt("beernumber", value);
        beereditor.commit();
        calculateBAC();
    }

    public void solobutton(View view){
        final SharedPreferences solotracker = getSharedPreferences("solobutton", Context.MODE_PRIVATE);
        final SharedPreferences.Editor soloeditor = solotracker.edit();
        int value = solotracker.getInt("solonumber",0) + 1;
        soloeditor.putInt("solonumber", value);
        soloeditor.commit();
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
        weightDouble = Double.parseDouble(tracker.getString("weight", ""));
        drinkDouble = drinknumber;
        timeDouble = Minutes%60 + 1;
        BACDouble = (((drinkDouble * 12 * 0.05) * 5.14) / (weightDouble * .73)) - (0.015 * timeDouble);
        DecimalFormat newDouble = new DecimalFormat("#.r###");
        BACshow.setText(newDouble.format(BACDouble) + " % BAC");

    }

    public void startparty(View view){

        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        Button timer = (Button)findViewById(R.id.startparty);
        if(timer.getText()=="Stop"){
            TimeBuff += MillisecondTime;

            handler.removeCallbacks(runnable);
        }
        timer.setText("Stop");


    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    public void onBackPressed(){
    }




}

