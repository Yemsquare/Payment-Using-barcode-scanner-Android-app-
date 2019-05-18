package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class barcodevalidation extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
   private static final String barcodevalidationURL="http://epagogic-apprehensi.000webhostapp.com/Barcode.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
}
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Prints scan results
        String getbars=rawResult.getText();
        if(getbars!=null) {

            Intent intent=new Intent(this,History.class);
            intent.putExtra("key", String.valueOf(getbars));
            //Toast.makeText(getApplicationContext(), getbars, Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,100);
        }
         rawResult.getBarcodeFormat().toString();
        // Prints the scan format (qrcode, pdf417 etc.)
        //Toast.makeText(getbars, "",Toast.LENGTH_SHORT).show();
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
