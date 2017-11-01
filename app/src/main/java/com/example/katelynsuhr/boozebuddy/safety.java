package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class safety extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        SharedPreferences safetylist = getSharedPreferences("numcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor numeditor = safetylist.edit();
    }

    public void contactview_main (View view) {
        Intent intent = new Intent(safety.this, contactsearch.class);
        startActivity(intent);
    }



}