package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
    public void diary_main (View view){
        Intent intent = new Intent(UserProfile.this, DiaryMain.class);
        startActivity(intent);
    }

    public void safety_main (View view){
        Intent intent = new Intent(UserProfile.this, safety.class);
        startActivity(intent);
    }

    public void blocked_main (View view){
        Intent intent = new Intent(UserProfile.this, blocked.class);
        startActivity(intent);
    }
}


