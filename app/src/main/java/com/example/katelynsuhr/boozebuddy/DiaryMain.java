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
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiaryMain extends AppCompatActivity {
    private ListView listView2;
    public CalendarAdapter adapter2;
    private String date;
    private Calendar calendar = Calendar.getInstance();

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarymain);
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);
       //
       // calendar.setTime(new Date());

       // calendar.set(Calendar.DAY_OF_MONTH);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                Toast toast = Toast.makeText(getBaseContext(),
                        "Selected Date is\n\n"+(month+1)+" / "+dayOfMonth+" / "+year,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_VERTICAL, 0, 0); //changes position Toast appears
                toast.show();
                date= "" + month + "_" + dayOfMonth + "_" + year;
               // mCalendarView.setDate(Long.parseLong(date));
              //  Date datePressed = calendar.getTime();
              //  calendar.setDate(Long.parseLong(date));

                SharedPreferences sharedPreferences = getSharedPreferences("DateDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("date",date);
                editor.apply();
                Toast toaster = Toast.makeText(getBaseContext(),"Date was saved successfully: "+sharedPreferences.contains("date"),Toast.LENGTH_LONG);
                toaster.show();
               // calendar = (Calendar) savedInstanceState.getSerializable("date");
//                if(BoozeUtil.isExist(DiaryMain.this,date)){
//                    Toast.makeText(DiaryMain.this,"TRUE"+ date, Toast.LENGTH_LONG).show();
//                }

                String stringTest = BoozeUtil.readFile(DiaryMain.this, date);
                List<String> items = Arrays.asList(stringTest.split("/"));
                List<Nutrition> nutritions = new ArrayList<>();

                Nutrition nutrition = null;
                for (int i = 0; i < items.size(); i++) {
                    int remainder = i % 3;

                    if (remainder == 0) {
                        nutrition = new Nutrition();
                        String m = items.get(i);
                        nutrition.setItemName(m);
                    } else if (remainder == 1) {
                        String n = items.get(i);
                        assert nutrition != null;
                        nutrition.setCalories(n);
                    } else if(remainder == 2) {
                        String p = items.get(i);
                        assert nutrition != null;
                        nutrition.setBrandName(p);
                        nutritions.add(nutrition);
                    }
                }
                listView2 = (ListView) findViewById(R.id.drink_listview);
                adapter2 = new CalendarAdapter(DiaryMain.this, nutritions);
                listView2.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();


            }
        });

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String currentDate = "" + month + "_"+ dayOfMonth+"_"+year;
        Toast.makeText(this,currentDate, Toast.LENGTH_LONG).show();
        String currentFile = BoozeUtil.readFile(this, currentDate);
        //Checks if there is a file already created for the current date, if there is one,
        //if populates the list view with the drinks from that day
        if(BoozeUtil.isExist(this,currentDate)){
            Toast.makeText(this,"TRUE"+ currentFile, Toast.LENGTH_LONG).show();
        }

        if(BoozeUtil.isExist(this, currentDate)){
            String stringTest = BoozeUtil.readFile(this, currentDate);
            List<String> items = Arrays.asList(stringTest.split("/"));
            List<Nutrition> nutritions = new ArrayList<>();

            Nutrition nutrition = null;
            for (int i = 0; i < items.size(); i++) {
                int remainder = i % 3;

                if (remainder == 0) {
                    nutrition = new Nutrition();
                    String m = items.get(i);
                    nutrition.setItemName(m);
                } else if (remainder == 1) {
                    String n = items.get(i);
                    assert nutrition != null;
                    nutrition.setCalories(n);
                } else if(remainder == 2) {
                    String p = items.get(i);
                    assert nutrition != null;
                    nutrition.setBrandName(p);
                    nutritions.add(nutrition);
                } else {
                    return;
                }
            }
            listView2 = (ListView) findViewById(R.id.drink_listview);
            adapter2 = new CalendarAdapter(this, nutritions);
            listView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

        } else {
            return;
        }
      
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("dateClicked", date);
    }


    public void foodClick (View view){
        Intent intent = new Intent(DiaryMain.this, DiarySearch.class);
        startActivity(intent);
    }
}
