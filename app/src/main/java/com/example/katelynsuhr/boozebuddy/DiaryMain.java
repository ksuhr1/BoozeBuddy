package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiaryMain extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarymain);
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                Toast toast = Toast.makeText(getBaseContext(),
                        "Selected Date is\n\n"+(month+1)+" / "+dayOfMonth+" / "+year,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_VERTICAL, 0, 0); //changes position Toast appears
                toast.show();
                String date= "" + month + "_" + dayOfMonth + "_" + year;
                SharedPreferences sharedPreferences = getSharedPreferences("DateDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("date",date);
                editor.apply();
                Toast toaster = Toast.makeText(getBaseContext(),"Date was saved successfully: "+sharedPreferences.contains("date"),Toast.LENGTH_LONG);
                toaster.show();
            }
        });

    }




    public void foodClick (View view){
        Intent intent = new Intent(DiaryMain.this, DiarySearch.class);
        startActivity(intent);
    }
}
