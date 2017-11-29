package com.example.katelynsuhr.boozebuddy;

        import java.util.ArrayList;

        import android.Manifest;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.provider.BaseColumns;
        import android.provider.BlockedNumberContract;
        import android.provider.ContactsContract;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.telecom.TelecomManager;

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
        BoozeFiles blocked = new BoozeFiles("blocked", "numbers", contactsearch.this);
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        blocked.writeFile(blocked, name);
        entercontact.setText(blocked.readFile(blocked));
    }

    public void addcontact (View view){
        BoozeFiles safety = new BoozeFiles("safety", "numbers", contactsearch.this);
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        safety.writeFile(safety, name);
        entercontact.setText(safety.readFile(safety));
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

    public void blockNumber(String number) {
        TelecomManager telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);
        contactsearch.this.startActivity(telecomManager.createManageBlockedNumbersIntent(), null);
        Cursor c = getContentResolver().query(BlockedNumberContract.BlockedNumbers.CONTENT_URI,
                new String[]{BlockedNumberContract.BlockedNumbers.COLUMN_ID,
                        BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER,
                        BlockedNumberContract.BlockedNumbers.COLUMN_E164_NUMBER}, null, null, null);

        ContentValues values = new ContentValues();
        values.put(BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER, number);
        Uri uri = getContentResolver().insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, values);

    }

}
