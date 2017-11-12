package com.example.katelynsuhr.boozebuddy;

        import java.util.ArrayList;

        import android.Manifest;
        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.ContentResolver;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.provider.BaseColumns;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactsearch);
        if (ContextCompat.checkSelfPermission(contactsearch.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(contactsearch.this,
                    Manifest.permission.READ_CONTACTS)) {

                Toast.makeText(contactsearch.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

            }
        }

    }
    public void searchcontacts (View view){
        EditText entercontact = (EditText)findViewById(R.id.entercontact);
        String name = entercontact.getText().toString();
        entercontact.setText(getContactDisplayNameByNumber(name));
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
