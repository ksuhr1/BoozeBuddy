//package com.example.katelynsuhr.boozebuddy;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.support.annotation.NonNull;
//import android.support.v4.content.res.ResourcesCompat;
//import android.support.v4.view.GestureDetectorCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.GestureDetector;
//import android.view.Gravity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.util.Log;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.AdapterView;
//import android.widget.CalendarView;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.List;
//import com.baoyz.swipemenulistview.SwipeMenu;
//import com.baoyz.swipemenulistview.SwipeMenuCreator;
//import com.baoyz.swipemenulistview.SwipeMenuItem;
//import com.baoyz.swipemenulistview.SwipeMenuListView;
//import java.util.Objects;
//
//import android.os.Handler;
//
//public class DiaryMain extends AppCompatActivity {
//
//
//    private static final int SWIPE_MIN_DISTANCE = 120;
//    private static final int SWIPE_MAX_OFF_PATH = 250;
//    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
//    // private GestureDetector detector = new GestureDetector(this, android.view.GestureDetector.OnGestureListener, Hand;er);
//    // private ListView listView2;
//    public CalendarAdapter adapter2;
//    private String date;
//    String currentDate;
//    List<Nutrition> nutritions;
//    Long dateChange;
//    // private Calendar calendar = Calendar.getInstance();
//
//    private static final String TAG = "CalendarActivity";
//    private CalendarView mCalendarView;
//
//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_diarymain);
//        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
//        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.background_color, null));
//
//        //dateChange = mCalendarView.getDate();
//
//        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView CalendarView, int year, int month, int dayOfMonth) {
//                Toast toast = Toast.makeText(getBaseContext(),
//                        "Selected Date is\n\n" + (month + 1) + " / " + dayOfMonth + " / " + year,
//                        Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP | Gravity.CENTER_VERTICAL, 0, 0); //changes position Toast appears
//                toast.show();
//                date = "" + month + "_" + dayOfMonth + "_" + year;
//                SharedPreferences sharedPreferences = getSharedPreferences("DateDetails", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("date", date);
//                editor.apply();
//                Toast toaster = Toast.makeText(getBaseContext(), "Date was saved successfully: " + sharedPreferences.contains("date"), Toast.LENGTH_LONG);
//                toaster.show();
//                // if(BoozeUtil.isExist(DiaryMain.this, date)) {
//                //   updateListView(date);
//                // }
//                updateListView(date);
//
//            }
//        });
//
//
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//        currentDate = "" + month + "_" + dayOfMonth + "_" + year;
//        Toast.makeText(this, currentDate, Toast.LENGTH_LONG).show();
//        String currentFile = BoozeUtil.readFile(this, currentDate);
//        updateListView(currentDate);
////        if (BoozeUtil.isExist(this, currentDate)) {
////            Toast.makeText(this, "TRUE" + currentFile, Toast.LENGTH_LONG).show();
////        }
//        //Checks if there is a file already created for the current date, if there is one,
//        //if populates the list view with the drinks from that day
//        if (BoozeUtil.isExist(this, currentDate)) {
//            updateListView(currentDate);
//        } else {
//            return;
//        }
//
//    }
//
//    /* List View helper function */
//    public void updateListView(String selectedDate) {
//        // if(BoozeUtil.isExist(DiaryMain.this, selectedDate)){
//        final String stringTest = BoozeUtil.readFile(DiaryMain.this, selectedDate);
//        // if(stringTest !=null) {
//        final List<String> items = Arrays.asList(stringTest.split("/"));
//        nutritions = new ArrayList<>();
//
//        Nutrition nutrition = null;
//        for (int i = 0; i < items.size(); i++) {
//            int remainder = i % 4;
//            if (remainder == 0) {
//                nutrition = new Nutrition();
//                String l = items.get(i);
//                nutrition.setItemId(l);
//            } else if (remainder == 1) {
//                String m = items.get(i);
//                assert nutrition != null;
//                nutrition.setItemName(m);
//            } else if (remainder == 2) {
//                String n = items.get(i);
//                assert nutrition != null;
//                nutrition.setCalories(n);
//            } else if (remainder == 3) {
//                String p = items.get(i);
//                assert nutrition != null;
//                nutrition.setBrandName(p);
//                nutritions.add(nutrition);
//            }
//        }
//        final SwipeMenuListView listView2 = (SwipeMenuListView)findViewById(R.id.drink_listview);
//        adapter2 = new CalendarAdapter(DiaryMain.this, nutritions);
//        listView2.setAdapter(adapter2);
//        adapter2.notifyDataSetChanged();
//
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "open" item
//                SwipeMenuItem openItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(66, 149,154)));
//                // set item width
//                openItem.setWidth(170);
//                // set item title
//                openItem.setTitle("View");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(170);
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_trash);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//
//        listView2.setMenuCreator(creator);
//        listView2.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                Nutrition selectedFromList = (Nutrition) listView2.getItemAtPosition(position);
//                switch (index) {
//                    case 0:
//                        Log.i("POSITION", selectedFromList.toString());
//                        Intent intent = new Intent(menu.getContext(), DrinkOutput.class);
//                        intent.putExtra("item_id", selectedFromList.getItemId());
//                        intent.putExtra("brand_name", selectedFromList.getBrandName());
//                        intent.putExtra("item_name", selectedFromList.getItemName());
//                        intent.putExtra("nf_calories", selectedFromList.getCalories());
//                        startActivity(intent);
//                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
//                        break;
//                    case 1:
//                        nutritions.remove(selectedFromList);
//                        Log.d(TAG, "Item"+selectedFromList.toString());
//                        Log.i("ItemsList ", items.toString());
//                        Toast.makeText(DiaryMain.this,stringTest.toString(),Toast.LENGTH_LONG).show();
//                        adapter2.notifyDataSetChanged();
//                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menu) {
//        switch (menu.getItemId()) {
//            case R.id.action_mainmenu:
//                Intent intent = new Intent(DiaryMain.this, MainActivity.class);
//                startActivity(intent);
//                return true;
//        }
//        return super.onOptionsItemSelected(menu);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_hamburger, menu);
//        return true;
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle state) {
//        super.onSaveInstanceState(state);
//        state.putSerializable("dateClicked", date);
//    }
//
//    public void foodClick (View view){
//        Intent intent = new Intent(DiaryMain.this, DiarySearch.class);
//        startActivity(intent);
//    }
//}
package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import java.util.Objects;

import android.os.Handler;

public class DiaryMain extends AppCompatActivity {


    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
   // private GestureDetector detector = new GestureDetector(this, android.view.GestureDetector.OnGestureListener, Hand;er);
   // private ListView listView2;
    public CalendarAdapter adapter2;
    private String date;
    List<Nutrition> nutritions;
    String stringTest;
    List<String> items;
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
                date = month + "_" + dayOfMonth + "_" + year;
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
        date = month + "_" + dayOfMonth + "_" + year;
      //  Toast.makeText(this, currentDate, Toast.LENGTH_LONG).show();
        String currentFile = BoozeUtil.readFile(this, date);
        SharedPreferences sharedPreferences = getSharedPreferences("DateDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date", date);
        editor.apply();
        Toast toaster = Toast.makeText(getBaseContext(), "Date was saved successfully: " + sharedPreferences.contains("date"), Toast.LENGTH_LONG);
      //  updateListView(currentDate);
//        if (BoozeUtil.isExist(this, currentDate)) {
//            Toast.makeText(this, "TRUE" + currentFile, Toast.LENGTH_LONG).show();
//        }
        //Checks if there is a file already created for the current date, if there is one,
        //if populates the list view with the drinks from that day
        if (BoozeUtil.isExist(this, date)) {
            Log.d("File is already created", "true");
            updateListView(date);
        } else {
            return;
        }

    }


    /* List View helper function */
    public void updateListView(String selectedDate) {
        // if(BoozeUtil.isExist(DiaryMain.this, selectedDate)){
        stringTest = BoozeUtil.readFile(DiaryMain.this, selectedDate);
        Log.i("stringTest", stringTest);
        // if(stringTest !=null) {
        items = Arrays.asList(stringTest.split("/"));
        nutritions = new ArrayList<>();
        final List<String> cal = new ArrayList<>();
        final int total = 0;

        Nutrition nutrition = null;
        String val;
        final TextView tv = (TextView) findViewById(R.id.addCalories);
       // List<String> cal;
        for (int i = 0; i < items.size(); i++) {
            int remainder = i % 4;
            String n;
            //Item id
            if (remainder == 0) {
                nutrition = new Nutrition();
                String l = items.get(i);
                nutrition.setItemId(l);
                //Item name
            } else if (remainder == 1) {
                String m = items.get(i);
                assert nutrition != null;
                nutrition.setItemName(m);
                //Item calories
            } else if (remainder == 2) {
                n = items.get(i);
                val = items.get(i);
                cal.add(val);
                Log.i("Calorie val", cal.toString());
                assert nutrition != null;
                nutrition.setCalories(n);
                //Item brand
            } else if (remainder == 3) {
                String p = items.get(i);
                assert nutrition != null;
                nutrition.setBrandName(p + "/");
                nutritions.add(nutrition);
            }
            int sum = 0;
            for(String num : cal) {
                Float a = Float.parseFloat(num);
                int b = (int)Math.round(a);
                sum+=b;
            }
            String stringCal = Float.toString(sum);
            Log.d("MyInt","Total Calories = "+sum);
//            TextView tv = (TextView) findViewById(R.id.addCalories);
            tv.setText(stringCal);
        }

        final SwipeMenuListView listView2 = (SwipeMenuListView)findViewById(R.id.drink_listview);
        adapter2 = new CalendarAdapter(DiaryMain.this, nutritions);
        listView2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(66, 149,154)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("View");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_trash);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView2.setMenuCreator(creator);
        listView2.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Nutrition selectedFromList = (Nutrition) listView2.getItemAtPosition(position);
                switch (index) {
                    case 0:
                        Log.i("POSITION", selectedFromList.toString());
                        Intent intent = new Intent(menu.getContext(), DrinkOutput.class);
                        intent.putExtra("item_id", selectedFromList.getItemId());
                        intent.putExtra("brand_name", selectedFromList.getBrandName());
                        intent.putExtra("item_name", selectedFromList.getItemName());
                        intent.putExtra("nf_calories", selectedFromList.getCalories());
                        startActivity(intent);
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                    case 1:
                        Log.i("POSITION", selectedFromList.toString());
                        Log.i("Nutritions Array List", nutritions.toString());
                        Log.i("File Data", stringTest);
                        Log.i("Items ", items.toString());
                        //items.get(selectedFromList);
                        String data1 = nutritions.get(position).getCalories();
                        cal.remove(data1);
                        Log.i("Remainder",cal.toString());
                        //int sum = 0;
                        String input = tv.getText().toString();
                        float sumInput = Float.parseFloat(input);
                        float deleteDrink = Float.parseFloat(data1);
                        int difference = (int) Math.round(sumInput - deleteDrink);
                        String result = Float.toString(difference);
                        Log.d("MyInt","Total Calories = "+result);
                        TextView tv = (TextView) findViewById(R.id.addCalories);
                        tv.setText(result);
                        Log.i("Selected Item", data1);
                        nutritions.remove(selectedFromList);
                        String itemLine = selectedFromList.getItemId();

                    //    String nutr = nutritions.toString();
                      //  Log.i("Nutritions", nutr);
                     //   BoozeUtil.writeFile(DiaryMain.this, nutr, date);

                      //  Log.i("Nutrition arraylist",nutritions.toString());
                       // Nutrition obj = listView2.getItemAtPosition(position).toString();
                       // nutritions.remove(obj);
                       // nutritions.remove(nutritions.indexOf(obj))
                       // ArrayList<String> drinkValues =

                       // cal.remove(selectedFromList);
                        Log.d(TAG, "Item"+selectedFromList.toString());
                        adapter2.notifyDataSetChanged();
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
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
