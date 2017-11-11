package com.example.katelynsuhr.boozebuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SearchItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        TextView input = (TextView) findViewById(R.id.textView1);

        Bundle extras = getIntent().getExtras();
        String data = extras.getString("list");
        input.append(data);
    }
}
