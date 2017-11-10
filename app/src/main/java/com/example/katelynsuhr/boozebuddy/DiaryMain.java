package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CalendarView;

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
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(DiaryMain.this,MainActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });
    }
    public void foodClick (View view){
        Intent intent = new Intent(DiaryMain.this, DiarySearch.class);
        startActivity(intent);
    }
}
