package com.example.katelynsuhr.boozebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;

public class DrinkOutput extends AppCompatActivity {
    // public static List<DrinkData> drinkDataList = new ArrayList<DrinkData>();
    private static List<String> charList = new ArrayList<String>();
    private ListView listView;
    public MacroNutrientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macronutrient_list);
        TextView input = (TextView) findViewById(R.id.textView1);
        Intent i = getIntent();
        Bundle b = i.getExtras();

        String item = (String) b.getString("item_id");
        sendItemRequest(item);

        TextView drinkName = (TextView)findViewById(R.id.drink);
        TextView brandName = (TextView)findViewById(R.id.brandName);
        TextView numServings = (TextView)findViewById(R.id.numServings);
        TextView cal = (TextView) findViewById((R.id.calories));


      //  getIntent().getStringExtra("brand_name");

       // drinkName.setText("item_name");
        brandName.setText(getIntent().getStringExtra("brand_name"));
       // numServings.setText("nf_serving_size_qty");
       // cal.setText("nf_calories");
    }

    public void sendItemRequest(String itemId) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String leftUrl = "https://api.nutritionix.com/v1_1/item";
        final String rightUrl = "?id=" + itemId + "&appId=82c97058&appKey=979eb4ea51a7fd11e7b5df0cae3dfd73";
        final String finalUrl = leftUrl + rightUrl;

        //https://api.nutritionix.com/v1_1/item?id=542edc2700a6af1719c326c3&appId=82c97058&appKey=979eb4ea51a7fd11e7b5df0cae3dfd73
        //static list contains id names nf_...
        //loop over list and create new list with a field of name and value and set to new array adapter and build array adapter

        //Request a string response form the provided URL
        JsonObjectRequest jsnRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", response.toString());
                        try {
                            JSONObject details = response;
                            Map<String, String> drinkMap = new TreeMap<String, String>();
                            drinkMap.put(replaceChar("nf_sugars"), details.getString("nf_sugars"));
                            drinkMap.put(replaceChar("nf_protein"), details.getString("nf_protein"));
                            drinkMap.put(replaceChar("nf_water_grams"), details.getString("nf_water_grams"));
                            drinkMap.put(replaceChar("nf_calories_from_fat"), details.getString("nf_calories_from_fat"));
                            drinkMap.put(replaceChar("nf_total_fat"), details.getString("nf_total_fat"));
                            drinkMap.put(replaceChar("nf_saturated_fat"), details.getString("nf_saturated_fat"));
                            drinkMap.put(replaceChar("nf_trans_fatty_acid"), details.getString("nf_trans_fatty_acid"));
                            drinkMap.put(replaceChar("nf_polyunsaturated_fat"), details.getString("nf_polyunsaturated_fat"));
                            drinkMap.put(replaceChar("nf_monounsaturated_fat"), details.getString("nf_monounsaturated_fat"));
                            drinkMap.put(replaceChar("nf_cholesterol"), details.getString("nf_cholesterol"));
                            drinkMap.put(replaceChar("nf_sodium"), details.getString("nf_sodium"));
                            drinkMap.put(replaceChar("nf_total_carbohydrate"), details.getString("nf_total_carbohydrate"));
                            drinkMap.put(replaceChar("nf_dietary_fiber"), details.getString("nf_dietary_fiber"));
                            drinkMap.put(replaceChar("nf_serving_size_qty"), details.getString("nf_serving_size_qty"));
                            drinkMap.put(replaceChar("nf_serving_weight_grams"), details.getString("nf_serving_weight_grams"));
                            drinkMap.put(replaceChar("nf_sugars"), details.getString("nf_sugars"));

                            // ArrayList<String> mKeys = new ArrayList<>(drinkMap.keySet());

                            listView = (ListView) findViewById(R.id.macroNutrientResults);
                            adapter = new MacroNutrientAdapter(DrinkOutput.this, drinkMap);
                            listView.setAdapter(adapter);
                            //ArrayList<String> mKeys = new ArrayList<>(drinkMap.keySet());


                            Log.i("VALUES", drinkMap.toString());
                            //   Log.i("NUTRITIONAL DATA", drinkDataList.get(0).getCalories().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        });
        requestQueue.add(jsnRequest);


    }

    private static String replaceChar(String str) {
        if (str != null && str.trim().length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                str = str.replaceAll("nf_", "");
                str = str.replaceAll("_", " ");
                // str = str.replaceAll("_", " ");
                //  str = str.trim().replaceFirst("\\s+","");
                str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            }
        }
        Log.i("CHAR", str);
        return str;

    }

}
