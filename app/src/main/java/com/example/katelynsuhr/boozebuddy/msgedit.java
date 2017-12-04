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
import android.widget.EditText;

public class msgedit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgedit);


    }

    public void savemsg(View view){
        EditText msg = (EditText)findViewById(R.id.editmsg);
        String message = msg.getText().toString();
        SharedPreferences custommsg = getSharedPreferences("custommsg", Context.MODE_PRIVATE);
        SharedPreferences.Editor newmsg = custommsg.edit();
        newmsg.putString("custommsg", custommsg.getString("custommsg", "") + message + "/");
        newmsg.commit();
        finish();
        Intent intent = new Intent (msgedit.this, SendText.class);
        startActivity(intent);
    }

}
