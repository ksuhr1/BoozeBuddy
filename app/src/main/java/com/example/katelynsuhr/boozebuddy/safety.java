package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
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

public class safety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        TextView safedisplay = (TextView)findViewById(R.id.safetydisplay);
        EditText safeedit = (EditText)findViewById(R.id.safetyedit);
        Button safeenter = (Button)findViewById(R.id.safetyenter);
        SharedPreferences safetylist = getSharedPreferences("numcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor numeditor = safetylist.edit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
