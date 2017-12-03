package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        final TextView name = (TextView)findViewById(R.id.nameshow);
        final TextView weight = (TextView)findViewById(R.id.weightshow);
        final TextView height = (TextView)findViewById(R.id.heightshow);
        final TextView age= (TextView)findViewById(R.id.ageshow);
        final TextView sex = (TextView)findViewById(R.id.sexshow);
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        name.setText(tracker.getString("name", "name"));
        weight.setText(tracker.getString("weight", "weight"));
        height.setText(tracker.getString("height", "height"));
        age.setText(tracker.getString("age", "age"));
        sex.setText(tracker.getString("sex", "sex"));
    }
    public void edituserinfo (View view){
        Intent intent = new Intent(UserInfo.this, EditInfo.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(UserInfo.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hamburger, menu);
        return true;
    }

}


