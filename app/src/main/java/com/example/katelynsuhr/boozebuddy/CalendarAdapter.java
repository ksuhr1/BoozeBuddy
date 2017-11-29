package com.example.katelynsuhr.boozebuddy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by katelynsuhr on 11/28/17.
 */
/*
public class CalendarAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Nutrition> drinks;
    private TextView itemName ;
    private TextView  brandName;
    private TextView calories ;

    public Adapter(Activity activity, List<Nutrition> items){
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.drink_list,null);

        }

        itemName=(TextView)convertView.findViewById(R.id.drinkName);
        brandName=(TextView)convertView.findViewById(R.id.brandName);
        calories=(TextView)convertView.findViewById(R.id.calories);

        Nutrition drink=drinks.get(position);

        itemName.setText(drink.getItemName());
        brandName.setText(drink.getBrandName());
        calories.setText(drink.getCalories());

        return convertView;
    }

}
*/