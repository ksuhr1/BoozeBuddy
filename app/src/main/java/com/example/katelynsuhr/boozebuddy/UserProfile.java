package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class UserProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final TextView profilename = (TextView)findViewById(R.id.profileName);
        profilename.setText(tracker.getString("name", "Hello!"));
        SharedPreferences safetylist = getSharedPreferences("safetylist", Context.MODE_PRIVATE);
        String safelist = safetylist.getString("safetylist", "");
        String name = "";
        int counter = 0;
        for( int i=0; i<safelist.length(); i++ ) {
            if( safelist.charAt(i) == '/' ) {
                counter++;
            }
        }
        String [] spinnerarray = new String[counter];
        int track= 0;
        for(int i = 0; i < safelist.length(); i++){
            if(safelist.charAt(i)== '/'){
                spinnerarray[track] = name;
                name = "";
                track++;
            } else {
                name = name + Character.toString(safelist.charAt(i));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.safetyspinner);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String name = sItems.getSelectedItem().toString();
                Toast.makeText(UserProfile.this, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
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
        finish();
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


    public void blockcontact(View view){
        TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        UserProfile.this.startActivity(telecomManager.createManageBlockedNumbersIntent(), null);
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


