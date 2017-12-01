package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrunkDial extends AppCompatActivity {
    ListView list;
    List listlist = new ArrayList();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drunk_dial);
        SharedPreferences toarray = getSharedPreferences("safetylist", Context.MODE_PRIVATE);
        String names = toarray.getString("safetylist", "");
        list = (ListView)findViewById(R.id.safetyview);
        String name = "";
        for(int i = 0; i < names.length(); i++){
            if(names.charAt(i)== '/'){
                listlist.add(name);
                name = "";
            } else {
                name = name + Character.toString(names.charAt(i));
            }
        }
        adapter = new ArrayAdapter(DrunkDial.this, R.layout.mytextview, listlist);
        list.setAdapter(adapter);
    }

}
