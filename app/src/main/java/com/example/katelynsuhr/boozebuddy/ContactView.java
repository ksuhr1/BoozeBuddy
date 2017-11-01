package com.example.katelynsuhr.boozebuddy;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactView extends Activity {

    EditText contact;
    TextView display;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        contact = findViewById(R.id.entercontact);
        display = findViewById(R.id.displaycontact);
    }

    public void searchcontact (View view) {
//            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
//            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS},
//                        REQUEST_CODE_ASK_PERMISSIONS);
//            }
        String content = contact.getText().toString();
        display.setText(getContactDisplayNameByNumber(content));
    }
    public String getContactDisplayNameByNumber(String number) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        String name = "?";

        ContentResolver contentResolver = getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

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



}
