package com.example.katelynsuhr.boozebuddy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

    public class MacroNutrientAdapter extends BaseAdapter {
        private Context context;

        private Map<String, String> map;
        private List<String> macros;

        public MacroNutrientAdapter(Context context, Map<String, String> map) {
            this.context = context;
            this.map = map;
            macros = new ArrayList<>(map.keySet());
        }

        @Override
        public int getCount() {
            return map.size();
        }

        @Override
        public String getItem(int position) {
            return macros.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View result;

            if (convertView == null) {
                result = LayoutInflater.from(parent.getContext()).inflate(R.layout.macronutrient_single_listview, parent, false);
            } else {
                result = convertView;
            }

            String nutrition = getItem(position);
            String value = map.get(nutrition);

            TextView nutrientName = (TextView) result.findViewById(R.id.nutrientName);
            TextView nutrientValue = (TextView) result.findViewById(R.id.nutrientValue);
            nutrientName.setText(nutrition);
            nutrientValue.setText(value);

            return result;
        }

    }


