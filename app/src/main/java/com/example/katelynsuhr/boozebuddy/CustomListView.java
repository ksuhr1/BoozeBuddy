package com.example.katelynsuhr.boozebuddy;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anishatavva on 12/1/17.
 */

public class CustomListView extends ArrayAdapter<String> {

    private String[] drinkname;
    private String[] desc;
    private Integer[] imgid;
    private Activity context;

    public CustomListView(Activity context, String[] drinkname, String[] desc, Integer[] imgid) {
        super(context, R.layout.listview_layout, drinkname);

        this.context=context;
        this.drinkname=drinkname;
        this.desc=desc;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;

        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }

        else {
            viewHolder= (ViewHolder) r.getTag();

        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(drinkname[position]);
        viewHolder.tvw2.setText(desc[position]);



        return r;


    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1 = (TextView) v.findViewById(R.id.drinkName);
            tvw2 = (TextView) v.findViewById(R.id.drinkDescription);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}
