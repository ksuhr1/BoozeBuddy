package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);
        final TextView name = (TextView)findViewById(R.id.nameval);
        final TextView weight = (TextView)findViewById(R.id.weightval);
        final TextView height = (TextView)findViewById(R.id.heightval);
        final TextView age= (TextView)findViewById(R.id.ageval);
        final TextView sex = (TextView)findViewById(R.id.sexval);
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        name.setText(tracker.getString("name", "name"));
        weight.setText(tracker.getString("weight", "weight"));
        height.setText(tracker.getString("height", "height"));
        age.setText(tracker.getString("age", "age"));
        sex.setText(tracker.getString("sex", "sex"));


    }

    public void saveinfo(View view){
        SharedPreferences tracker = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor infoeditor = tracker.edit();
        final EditText name = (EditText)findViewById(R.id.nameval);
        final EditText weight = (EditText)findViewById(R.id.weightval);
        final EditText height = (EditText)findViewById(R.id.heightval);
        final EditText age = (EditText)findViewById(R.id.ageval);
        final EditText sex = (EditText)findViewById(R.id.sexval);
        infoeditor.putString("name" , name.getText().toString());
        infoeditor.putString("weight" , weight.getText().toString());
        infoeditor.putString("height" , height.getText().toString());
        infoeditor.putString("age" , age.getText().toString());
        infoeditor.putString("sex" , sex.getText().toString());
        infoeditor.commit();
        finish();

    }

    public void editcontacts(View view){
        Intent intent = new Intent(EditInfo.this, contactsearch.class);
        startActivity(intent);
    }

}

