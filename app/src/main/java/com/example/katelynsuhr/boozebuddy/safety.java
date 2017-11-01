package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.provider.ContactsContract;import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.widget.AdapterView;

public class safety extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        SharedPreferences safetylist = getSharedPreferences("numcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor numeditor = safetylist.edit();
    }
    public void contactview_main (View view){
        Intent intent = new Intent(safety.this, ContactView.class);
        startActivity(intent);
    }




}