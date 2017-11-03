package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class IntroMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_menu);
    }

    public void homeClick(View view){
        Intent intent = new Intent(IntroMenu.this, IntroMenu.class);
        startActivity(intent);
    }




}
