package com.example.katelynsuhr.boozebuddy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_menu);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        Intent intent = new Intent("tips.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, intent , PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), broadcast );
    }

    public void diary_main (View view){
        Intent intent = new Intent(MainActivity.this, DiaryMain.class);
        startActivity(intent);
    }

    public void intro_party (View view){
        Intent intent = new Intent(MainActivity.this, partymode.class);
        startActivity(intent);
    }

    public void intro_profile (View view){
        Intent intent = new Intent(MainActivity.this, UserProfile.class);
        startActivity(intent);
    }

    public void drunkdial (View view){
        Intent intent = new Intent(MainActivity.this, DrunkDial.class);
        startActivity(intent);
    }


}
