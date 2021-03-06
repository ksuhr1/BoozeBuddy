package com.example.katelynsuhr.boozebuddy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katelynsuhr on 11/28/17.
 */

public class CalendarAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private List<Nutrition> drinks;

    private TextView itemName ;
    private TextView  brandName;
    private TextView calories ;

    public CalendarAdapter(Activity activity, List<Nutrition> items){
        this.activity = activity;
        this.drinks = items;
    }
    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clearData() {
        // clear the data
        drinks.clear();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // View rowView = convertView;



        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.drink_list,null);

        }

        itemName= convertView.findViewById(R.id.drinkName);
        brandName= convertView.findViewById(R.id.brandName);
        calories= convertView.findViewById(R.id.calories);
       // totalCal = convertView.findViewById(R.id.addCalories);

        Nutrition drink=drinks.get(position);

        itemName.setText(drink.getItemName());
        brandName.setText(drink.getBrandName());
        calories.setText(drink.getCalories());
//        totalCal.setText(drink.getTotalCal());

        return convertView;
    }

}
