package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    public void party_main (View view){
        Intent intent = new Intent(UserProfile.this, partymode.class);
        startActivity(intent);
    }

    public void userinfo (View view) {
        Intent intent = new Intent(UserProfile.this, UserInfo.class);
        startActivity(intent);
    }

}


