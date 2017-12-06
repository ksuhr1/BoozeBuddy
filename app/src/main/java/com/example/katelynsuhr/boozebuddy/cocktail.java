package com.example.katelynsuhr.boozebuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class cocktail extends AppCompatActivity {

    ListView lst;
    String[] drinkname={"MOJITO", "MARGARITA", "MARTINI-EXTRA DRY" , "PINA COLADA", "SCREWDRIVER", "MARTINI-TRADITIONAL", "BOURBON AND WATER", "VODKA AND TONIC", "GIN AND TONIC", "COSMOPOLITAN"};
    String[] desc={"1.3 U.S. standard drinks\n" +
            "and has 13.3% alcohol\n" +
            "in 6 total fluid ounces.", "1.7 U.S. standard drinks\n" +
            "and has 33.3% alcohol\n" +
            "in 3 total fluid ounces.", "1.4 U.S. standard drinks\n" + "and has 37.3% alcohol\n" + "in 2.25 total fluid ounces.",
            "2.0 U.S. standard drinks\n" + "and has 13.3% alcohol\n" + "in 9 total fluid ounces.", "1.3 U.S. standard drinks\n" + "and has 11.4% alcohol\n" + "in 7 total fluid ounces.",
            "1.2 U.S. standard drinks\n" + "and has 32.0% alcohol\n" + "in 2.25 total fluid ounces.", "1.3 U.S. standard drinks\n" + "and has 13.3% alcohol\n" + "in 6 total fluid ounces.",
            "1.3 U.S. standard drinks\n" + "and has 13.3% alcohol\n" + "in 6 total fluid ounces.", "1.6 U.S. standard drinks\n" + "and has 13.4% alcohol\n" + "in 7 total fluid ounces.",
            "1.3 U.S. standard drinks\n" + "and has 27.3% alcohol\n" + "in 2.75 total fluid ounces."};
    Integer[] imgid = {R.drawable.ccimage1, R.drawable.ccimage2, R.drawable.ccimage3, R.drawable.ccimage4, R.drawable.ccimage5,
            R.drawable.ccimage6, R.drawable.ccimage7, R.drawable.ccimage8, R.drawable.ccimage9, R.drawable.ccimage10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cocktail);

        lst = (ListView) findViewById(R.id.listView);
        CustomListView customListView = new CustomListView(this,drinkname,desc,imgid);
        lst.setAdapter(customListView);


    }

}
