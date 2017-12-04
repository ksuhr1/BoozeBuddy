package com.example.katelynsuhr.boozebuddy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SendText extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ListView list;
    List<String> listlist = new ArrayList();
    ArrayAdapter adapter;
    String phoneNo, message, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text);
        String sos = "Please come help me";
        String barf = "I feel like I'm about to throw up";
        String dondeestats = "Where are you?";
        list = (ListView)findViewById(R.id.messageview);
        listlist.add(sos);
        listlist.add(barf);
        listlist.add(dondeestats);
        adapter = new ArrayAdapter(SendText.this, R.layout.mytextview2, listlist);
        list.setAdapter(adapter);
        SharedPreferences textname = getSharedPreferences("textname", Context.MODE_PRIVATE);
        name = textname.getString("name", "");
        SharedPreferences newmsg = getSharedPreferences("custommsg", Context.MODE_PRIVATE);
        String custommsg = newmsg.getString("custommsg", "");
        String singlemsg = "";
        int track = 0;
        for(int i = 0; i < custommsg.length(); i++){
            if(custommsg.charAt(i)== '/'){
                listlist.add(singlemsg);
                singlemsg = "";
                track++;
            } else {
                singlemsg = singlemsg + Character.toString(custommsg.charAt(i));
            }
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendSMSMessage(name, list.getItemAtPosition(position).toString());
            }

        });


    }

    public void addmsg(View view){
        Intent intent = new Intent(SendText.this, msgedit.class);
        startActivity(intent);
    }

    protected void sendSMSMessage(String name, String msg) {
        phoneNo = getPhoneNumber(name, SendText.this);
        message = msg;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);

            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "Message successfully sent",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    public String getPhoneNumber(String name, Context context) {
        String ret = null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        if(ret==null)
            ret = "Unsaved";
        return ret;
    }

}
