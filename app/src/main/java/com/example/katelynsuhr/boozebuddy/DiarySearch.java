package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiarySearch extends AppCompatActivity {
    public static List<Nutrition> nutritionList = new ArrayList<>();
    private ListView listView;
    public Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_search);

        listView = (ListView) findViewById(R.id.jsonResults);
        adapter = new Adapter(this, nutritionList);
        listView.setAdapter(adapter);
    }

    public void scanRequest(View view) {
        Intent intent = new Intent(DiarySearch.this, Scanner.class);
        startActivity(intent);
    }


    //When user hits search
    public void sendRequest(View view) {
        TextView input = (TextView) findViewById(R.id.drinkInput);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String leftUrl = "https://api.nutritionix.com/v1_1/search/";
        final String rightUrl = "?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id%2Cnf_calories&appId=82c97058&appKey=979eb4ea51a7fd11e7b5df0cae3dfd73";
        final String finalUrl = leftUrl +input.getText()+ rightUrl;
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }

        nutritionList.clear();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nutrition selectedFromList = (Nutrition) listView.getItemAtPosition(position);
                Log.i("POSITION", selectedFromList.toString());
                Intent intent = new Intent(view.getContext(), DrinkOutput.class);
                intent.putExtra("item_id", selectedFromList.getItemId());
                intent.putExtra("brand_name", selectedFromList.getBrandName());
                intent.putExtra("item_name", selectedFromList.getItemName());
                intent.putExtra("nf_calories", selectedFromList.getCalories());
                startActivity(intent);

            }
        });

        //Request a string response form the provided URL
        JsonObjectRequest jsnRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String field = obj.getString("fields");
                                JSONObject details = new JSONObject(field);
                                Nutrition drink = new Nutrition();
                                drink.setItemName(details.getString("item_name"));
                                drink.setBrandName(details.getString("brand_name"));
                                drink.setCalories(details.getString("nf_calories"));
                                drink.setItemId(details.getString("item_id"));
                                nutritionList.add(drink);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work");
                Log.e("VOLLEY", error.toString());
            }
        });
        requestQueue.add(jsnRequest);



    }


}
