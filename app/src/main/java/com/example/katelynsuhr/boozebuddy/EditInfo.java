package com.example.katelynsuhr.boozebuddy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        final TextView name = (TextView)findViewById(R.id.nameval);
        final TextView weight = (TextView)findViewById(R.id.weightval);
        final TextView age= (TextView)findViewById(R.id.ageval);
        final Spinner sex = (Spinner) findViewById(R.id.sexval);
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        name.setText(tracker.getString("name", "name"));
        weight.setText(tracker.getString("weight", "weight"));
        age.setText(tracker.getString("age", "0"));
        List<String> list = new ArrayList();
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        sex.setAdapter(adapter);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if(sex.getSelectedItem().toString() == "Female") {
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.SECOND, 5);

                    Intent intent = new Intent("tips.action.DISPLAY_NOTIFICATION");
                    PendingIntent broadcast = PendingIntent.getBroadcast(EditInfo.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });




    }

    public void saveinfo(View view){
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor infoeditor = tracker.edit();
        final EditText name = (EditText)findViewById(R.id.nameval);
        final EditText weight = (EditText)findViewById(R.id.weightval);
        final EditText age = (EditText)findViewById(R.id.ageval);
        final Spinner sex = (Spinner)findViewById(R.id.sexval);
        infoeditor.putString("name" , name.getText().toString());
        infoeditor.putString("weight" , weight.getText().toString());
        infoeditor.putString("age" , (age.getText().toString()));
        infoeditor.putString("sex" , sex.getSelectedItem().toString());
        infoeditor.commit();
        finish();

    }

    public void editcontacts(View view){
        Intent intent = new Intent(EditInfo.this, contactsearch.class);
        startActivity(intent);
    }



}