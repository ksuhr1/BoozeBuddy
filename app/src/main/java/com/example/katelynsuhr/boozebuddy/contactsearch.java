package com.example.katelynsuhr.boozebuddy;

        import java.util.ArrayList;

        import android.Manifest;
        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.ContentResolver;
        import android.content.ContentValues;
        import android.content.Context;
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
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListView;
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
        entercontact.setText(getContactDisplayNameByNumber(name));
    }

    public void addcontact (View view){
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        entercontact.setText(getContactDisplayNameByNumber(name));
    }

    public String getContactDisplayNameByNumber(String number) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "?";

        ContentResolver contentResolver = getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                //String contactId = contactLookup.getString(contactLookup.getColumnIndex(BaseColumns._ID));
            }
        } finally {
            if (contactLookup != null) {
                contactLookup.close();
            }
        }

        return name;
    }

    public void blockNumber(String number) {
        Context.startActivity(telecomManager.createManageBlockedNumbersIntent(), null);
        Cursor c = getContentResolver().query(BlockedNumberContract.BlockedNumbers.CONTENT_URI,
                new String[]{BlockedNumberContract.BlockedNumbers.COLUMN_ID,
                        BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER,
                        BlockedNumberContract.BlockedNumbers.COLUMN_E164_NUMBER}, null, null, null);


        ContentValues values = new ContentValues();
        values.put(BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "1234567890");
        Uri uri = getContentResolver().insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, values);

        ContentValues values = new ContentValues();
        values.put(BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "1234567890");
        Uri uri = getContentResolver().insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, values);
        getContentResolver().delete(uri, null, null);

    }

}
