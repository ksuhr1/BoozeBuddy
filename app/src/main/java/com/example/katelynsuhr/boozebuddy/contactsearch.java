package com.example.katelynsuhr.boozebuddy;

        import java.util.ArrayList;

        import android.Manifest;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.provider.BaseColumns;
        import android.provider.BlockedNumberContract;
        import android.provider.ContactsContract;
        import android.support.design.widget.Snackbar;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
        import android.text.TextUtils;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.telecom.TelecomManager;
        import android.widget.TextView;
        import android.widget.Toast;

public class contactsearch extends Activity {
    private ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsearch);
        if (ContextCompat.checkSelfPermission(contactsearch.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(contactsearch.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(contactsearch.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    public void blockcontact (View view){
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        Toast toast = Toast.makeText(contactsearch.this, getPhoneNumber(name, contactsearch.this), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 10 , 10);
        toast.show();
        blockNumber();
    }

    public void addcontact (View view){
        SharedPreferences safetylist = getSharedPreferences("safetylist", Context.MODE_PRIVATE);
        SharedPreferences.Editor safetyedit = safetylist.edit();
        SharedPreferences safetynumbers = getSharedPreferences("safetynumbers", Context.MODE_PRIVATE);
        SharedPreferences.Editor safetynumedit = safetynumbers.edit();
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        String number = getPhoneNumber(name, this);
        if(number == "Unsaved"){
            Toast.makeText(this, "That is not a valid contact", Toast.LENGTH_SHORT).show();
            return;
        }
        safetynumedit.putString("safetynumbers", safetynumbers.getString("safetynumbers", "") + number + "/");
        safetynumedit.commit();
        safetyedit.putString("safetylist", safetylist.getString("safetylist", "") + name + "/");
        safetyedit.commit();
        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
    }

    public void deletecontact (View view){
        SharedPreferences safetylist = getSharedPreferences("safetylist", Context.MODE_PRIVATE);
        SharedPreferences.Editor safetyedit = safetylist.edit();
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        ArrayList<String> names = toArray("safetylist");
        names.remove(names.indexOf(entercontact.getText().toString() + "/"));
        String joined = "";
        for(int i = 0; i < names.size(); i++){
            joined += names.get(i);
        }
        Toast.makeText(this, joined, Toast.LENGTH_SHORT).show();
        safetyedit.putString("safetylist", joined);
        safetyedit.commit();
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

    public void blockNumber() {
        TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        contactsearch.this.startActivity(telecomManager.createManageBlockedNumbersIntent(), null);
    }

    public ArrayList<String> toArray (String category) {
        SharedPreferences toarray = getSharedPreferences(category, Context.MODE_PRIVATE);
        String names = toarray.getString(category, "");
        String name = "";
        ArrayList<String> newarray = new ArrayList<String>();
        for(int i = 0; i < names.length(); i++){
            if(names.charAt(i)== '/'){
                newarray.add(name + "/");
                name = "";
            } else {
                name = name + Character.toString(names.charAt(i));
            }
        }
        return newarray;
    }


}
