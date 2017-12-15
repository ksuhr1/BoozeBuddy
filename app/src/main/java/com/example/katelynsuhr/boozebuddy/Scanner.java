package com.example.katelynsuhr.boozebuddy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.Manifest;
import android.content.DialogInterface;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
       // setContentView(R.layout.activity_diary_search);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(Scanner.this, "Permission is granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return(ContextCompat.checkSelfPermission(Scanner.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
//    {
//        // This is because the dialog was cancelled when we recreated the activity.
//        if (permissions.length == 0 || grantResults.length == 0)
//            return;
//
//        switch (requestCode)
//        {
//            case REQUEST_CAMERA:
//            {
//                     if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                     {
//                        Toast.makeText(Scanner.this, "Permission Granted", Toast.LENGTH_LONG).show();
//                         onResume();
//                     }
//
//                    else
//                    {
//                        Toast.makeText(Scanner.this, "Permission Denied", Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//              //  }
//            }
//            break;
//        }
//    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[])
    {
        switch(requestCode) {
            case REQUEST_CAMERA :
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(Scanner.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Scanner.this, "Permission Denied", Toast.LENGTH_LONG).show();
                       // if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        //{
                           // if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
                           // {
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                               // if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                               // {
                                                 //   requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                                               // }
                                            }
                                        });
                                return;
                          //  }
                        //}
                    }
                }
                break;
        }
    }

  //  @Override
    public void startCamera() {
        if (checkPermission()) {
            if (scannerView == null) {
                scannerView = new ZXingScannerView(this);
                setContentView(scannerView);
            }
            scannerView.setResultHandler(this);
            System.out.println("fsdkfhjbsdAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            scannerView.startCamera();
        } else {
            requestPermission();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startCamera();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                if(scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
              //  System.out.println("fsdkfhjbsdAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                scannerView.startCamera();
            }
            else
            {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(Scanner.this)
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
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i){
//                scannerView.resumeCameraPreview(Scanner.this);
//            }
//        });
        if(scanResult.length() == 12) {
            builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences sharedP = getSharedPreferences("ScanResult", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedP.edit();
                    editor.putString("scanResult", scanResult);
                    editor.putBoolean("scanned", true);
                    editor.apply();
                    Intent intent = new Intent(Scanner.this, DiarySearch.class);
                    startActivity(intent);
                    // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                    // startActivity(intent);

                }
            });
        }
        if(scanResult.length()<12 || scanResult.length()>12){
            builder.setTitle("Please scan a barcode with 12 digits, this has "+scanResult.length()+" digits");
            builder.setMessage(scanResult);
            builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                scannerView.resumeCameraPreview(Scanner.this);
            }
        });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            builder.setMessage(scanResult);
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
}

