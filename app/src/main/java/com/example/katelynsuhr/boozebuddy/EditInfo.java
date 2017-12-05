package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
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
        age.setText(Integer.toString(tracker.getInt("age", 0)));
        List<String> list = new ArrayList();
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        sex.setAdapter(adapter);


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