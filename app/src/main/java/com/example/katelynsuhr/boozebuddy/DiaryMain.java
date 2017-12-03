package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.AdapterView;
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
    String currentDate;
    Long dateChange;
    // private Calendar calendar = Calendar.getInstance();

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarymain);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.background_color, null));

        //dateChange = mCalendarView.getDate();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView CalendarView, int year, int month, int dayOfMonth) {
                Toast toast = Toast.makeText(getBaseContext(),
                        "Selected Date is\n\n" + (month + 1) + " / " + dayOfMonth + " / " + year,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, 0); //changes position Toast appears
                toast.show();
                date = "" + month + "_" + dayOfMonth + "_" + year;
                SharedPreferences sharedPreferences = getSharedPreferences("DateDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("date", date);
                editor.apply();
                Toast toaster = Toast.makeText(getBaseContext(), "Date was saved successfully: " + sharedPreferences.contains("date"), Toast.LENGTH_LONG);
                toaster.show();
                // if(BoozeUtil.isExist(DiaryMain.this, date)) {
                //   updateListView(date);
                // }
                updateListView(date);

            }
        });


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        currentDate = "" + month + "_" + dayOfMonth + "_" + year;
        Toast.makeText(this, currentDate, Toast.LENGTH_LONG).show();
        String currentFile = BoozeUtil.readFile(this, currentDate);
        updateListView(currentDate);
        if (BoozeUtil.isExist(this, currentDate)) {
            Toast.makeText(this, "TRUE" + currentFile, Toast.LENGTH_LONG).show();
        }
        //Checks if there is a file already created for the current date, if there is one,
        //if populates the list view with the drinks from that day
        if (BoozeUtil.isExist(this, currentDate)) {
            updateListView(currentDate);
        } else {
            return;
        }
    }
//    public void setBackground(Drawable background) {
//        //noinspection deprecation
//        setBackgroundDrawable(background);
//    }
//
//    @Deprecated
//    public void setBackgroundDrawable(Drawable background) {
//        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_color));
//    }

    /* List View helper function */
    public void updateListView(String selectedDate) {
        // if(BoozeUtil.isExist(DiaryMain.this, selectedDate)){
        String stringTest = BoozeUtil.readFile(DiaryMain.this, selectedDate);
        // if(stringTest !=null) {
        List<String> items = Arrays.asList(stringTest.split("/"));
        List<Nutrition> nutritions = new ArrayList<>();

        Nutrition nutrition = null;
        for (int i = 0; i < items.size(); i++) {
            int remainder = i % 4;

            if (remainder == 0) {
                nutrition = new Nutrition();
                String l = items.get(i);
                nutrition.setItemId(l);
            } else if (remainder == 1) {
                //nutrition = new Nutrition();
                String m = items.get(i);
                assert nutrition != null;
                nutrition.setItemName(m);
            } else if (remainder == 2) {
                String n = items.get(i);
                assert nutrition != null;
                nutrition.setCalories(n);
            } else if (remainder == 3) {
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
//        }else{
//            return;
//        }
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nutrition selectedFromList = (Nutrition) listView2.getItemAtPosition(position);
                Log.i("POSITION", selectedFromList.toString());
                Intent intent = new Intent(view.getContext(), DrinkOutput.class);
                intent.putExtra("item_id", selectedFromList.getItemId());
                intent.putExtra("brand_name", selectedFromList.getBrandName());
                intent.putExtra("item_name", selectedFromList.getItemName());
                intent.putExtra("nf_calories", selectedFromList.getCalories());
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(DiaryMain.this, MainActivity.class);
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
