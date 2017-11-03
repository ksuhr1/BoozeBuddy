package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_menu);
    }

    public void diary_main (View view){
        Intent intent = new Intent(MainActivity.this, DiaryMain.class);
        startActivity(intent);
    }

    public void intro_party (View view){
        Intent intent = new Intent(MainActivity.this, partymode.class);
        startActivity(intent);
    }

    public void intro_profile (View view){
        Intent intent = new Intent(MainActivity.this, UserProfile.class);
        startActivity(intent);
    }


}
