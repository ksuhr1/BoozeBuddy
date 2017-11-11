package com.example.katelynsuhr.boozebuddy;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class DiarySearch extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String>items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_search);

    }

    //When user hits search
    public void sendRequest(View view){
        TextView input = (TextView) findViewById(R.id.drinkInput);
            try {
                final JSONObject jsonBody = new JSONObject();
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                String url = "https://api.nutritionix.com/v1_1/search";
                jsonBody.put("appId", "82c97058");
                jsonBody.put("appKey", "979eb4ea51a7fd11e7b5df0cae3dfd73");
                jsonBody.put("query", input.getText());
//                jsonBody.put("results","0:20");
//                jsonBody.put("cal_min", "0");
//                jsonBody.put("cal_max","50000");
//                jsonBody
                final ListView listView = (ListView) findViewById(R.id.jsonResults);
                items = new ArrayList<>();
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, items);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedFromList = (String) (listView.getItemAtPosition(position));
                        System.out.print(position);
                        System.out.print(selectedFromList);
                        Intent intent = new Intent(view.getContext(), SearchItem.class);
                        intent.putExtra("list", selectedFromList);
                        startActivity(intent);
                      //  myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       // myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      //  myIntent.putExtra("id", position);
                      //  startActivityForResult(myIntent, 0);
                    }
                });

                //Request a string response form the provided URL
                JsonObjectRequest jsnRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("VOLLEY", response.toString());
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");
                                for(int i = 0; i< jsonArray.length(); i++){
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    String field = obj.getString("fields");
                                    JSONObject details = new JSONObject(field);
                                    String name = details.getString("item_name");
                                    String brand = details.getString("brand_name");
                                  // String calories = details.getString("nf_calories");
                                    items.add(name+"\n"+brand+"\n"+"\n\n");
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
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

        }
//}


        //implements ZXingScannerView.ResultHandler{
/*
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
       // setContentView(R.layout.activity_diary_search);

       // if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
       // {
            if(checkPermission())
            {
                Toast.makeText(DiarySearch.this, "Permission is granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
       // }
    }

    private boolean checkPermission()
    {
        return(ContextCompat.checkSelfPermission(DiarySearch.this, CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermssionsResult(int requestCode, String permission[], int grantResults[])
    {
        switch(requestCode)
        {
            case REQUEST_CAMERA :
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(DiarySearch.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(DiarySearch.this, "Permission Denied", Toast.LENGTH_LONG).show();
                       // if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                       // {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayAlertMessage("You need to allow access for both permissions",
                                       // new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                              //  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                               // }
                                            }
                                        });
                                return;
                            }
                       // }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

      //  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
       // {
            if(checkPermission())
            {
                if(scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestPermission();
            }
       // }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(DiarySearch.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }

    @Override
    public void handleResult(Result result)
    {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                scannerView.resumeCameraPreview(DiarySearch.this);
            }
        });
        builder.setNeutralButton("Visit",// new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(intent);

            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();

    }
}
*/