package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import java.util.List;

public class DiarySearch extends AppCompatActivity {
    public static List<Nutrition> nutritionList = new ArrayList<>();
    private ListView listView;
    public Adapter adapter;
    SharedPreferences appIntro;
   // SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_search);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.background_color, null));

        listView = (ListView) findViewById(R.id.jsonResults);
        adapter = new Adapter(this, nutritionList);
        listView.setAdapter(adapter);
        SharedPreferences sharedP = this.getSharedPreferences("ScanResult", Context.MODE_PRIVATE);
            String scanned = sharedP.getString("scanResult", "none");
            if(scanned.equals("none")) {
            }
            else
            {
                Log.d("Scanned result", scanned);
                String xscan = replaceChar(scanned);
                Long yscan = Long.valueOf(xscan);
                System.out.print("Long+" + yscan);
                System.out.print(yscan + "zscan");
                if (yscan != 0) {
                    scannedRequest(yscan);

                }
        }

    }

    public static void removeSharedPreferences(Context context) {
        SharedPreferences sharedP = context.getSharedPreferences("ScanResult", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedP.edit();
        editor.clear();
        editor.commit();
    }
    private static String replaceChar(String str) {
        for (int i = 0, length = str.length() - 1; i < length; i++) {
            if (str.charAt(i) != '0') {
                str = str.substring(i);
                break;
            }
        }
        System.out.println(str);
        return str;
    }

    public void scannedRequest(Long input) {
        //TextView input = (TextView) findViewById(R.id.drinkInput);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String leftUrl = "https://api.nutritionix.com/v1_1/item?upc=";
        final String rightUrl = "&appId=82c97058&appKey=979eb4ea51a7fd11e7b5df0cae3dfd73";
        final String finalUrl = leftUrl +input+ rightUrl;
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
        JsonObjectRequest jsnRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", response.toString());
                        try {
                                Nutrition drink = new Nutrition();
                                drink.setItemName(response.getString("item_name"));
                                drink.setBrandName(response.getString("brand_name"));
                                drink.setCalories(response.getString("nf_calories"));
                                drink.setItemId(response.getString("item_id"));
                                nutritionList.add(drink);
                            } catch (JSONException e1) {
                            e1.printStackTrace();
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

    public void scanRequest(View view) {
        Intent intent = new Intent(DiarySearch.this, Scanner.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.action_mainmenu:
                Intent intent = new Intent(DiarySearch.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hamburger, menu);
        return true;
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
        JsonObjectRequest jsnRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null,
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
