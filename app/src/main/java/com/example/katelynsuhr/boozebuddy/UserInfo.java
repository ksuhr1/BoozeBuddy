package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        final TextView name = (TextView)findViewById(R.id.nameshow);
        final TextView weight = (TextView)findViewById(R.id.weightshow);
        final TextView age= (TextView)findViewById(R.id.ageshow);
        final TextView sex = (TextView)findViewById(R.id.sexshow);
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        name.setText(tracker.getString("name", "name"));
        weight.setText(tracker.getString("weight", "weight"));
        age.setText(tracker.getString("age", "0"));
        sex.setText(tracker.getString("sex", "sex"));
    }
    public void edituserinfo (View view){
        finish();
        Intent intent = new Intent(UserInfo.this, EditInfo.class);
        startActivity(intent);

    }

}