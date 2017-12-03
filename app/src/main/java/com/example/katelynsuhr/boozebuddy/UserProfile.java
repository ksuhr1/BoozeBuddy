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
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final TextView profilename = (TextView)findViewById(R.id.profileName);
        profilename.setText(tracker.getString("name", "Hello!"));

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

    public void baccalc (View view){
        Intent intent = new Intent(UserProfile.this, BACCalculator.class);
        startActivity(intent);
    }

    public void spendingcalculator(View view){
        Intent intent = new Intent(UserProfile.this, spendingcalculator.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
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

//    public void filetest(View view){
//        BoozeFiles file = new BoozeFiles("test", "testcategory", UserProfile.this);
//        file.deleteFile(file);
//        file.writeDrink(file, "Beer", "300", "glucose, lactose");
//        file.writeDrink(file, "Vodka", "300", "glucose, lactose");
//        file.writeDrink(file, "Wine", "300", "glucose, lactose");
//        String stringtest = file.readFile(file);
//        final TextView profilename = (TextView)findViewById(R.id.profileName);
//        profilename.setText(file.readNutrients(file));
//        Toast.makeText(UserProfile.this, stringtest, Toast.LENGTH_SHORT).show();
//    }

}


