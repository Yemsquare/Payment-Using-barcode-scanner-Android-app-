package com.smartshopping.sqlexamples;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class talktime extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 0;
    private static final int MY_PERMISSION_STORAGE = 1;
    Button btnscan;
    TextView display;
    SurfaceView cameraview;
    //BarcodeDetector detector;
    EditText txt;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RequestCameraPermissionID:
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    try {
                        cameraSource.start(cameraview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talktime);
        display =(TextView) findViewById(R.id.text);
        txt= (EditText) findViewById(R.id.talk);
        btnscan =(Button) findViewById(R.id.scan);
        cameraview =(SurfaceView) findViewById(R.id.scanview);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("NavActivity", "Detector dependencies are not yet available");
        } else {
            cameraSource= new CameraSource.Builder(getApplicationContext(),textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(800,600)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraview.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        cameraSource.start(cameraview.getHolder());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                  width=10;
                    height=10;


                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });


            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {

                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size() !=0){
                        display.post(new Runnable() {
                            @Override
                            public void run() {
                                final StringBuilder stringBuilder=new StringBuilder();
                                for (int i=0;i<items.size();++i){
                                    TextBlock item=items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    //stringBuilder.append("\n");
                                }
                                //display
                                txt.setText(stringBuilder.toString());

                                //   display.getText().toString();
                                btnscan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int talktime=Integer.parseInt(txt.getText().toString());
                                        String number="*113*"+ talktime + Uri.encode("#");
                                        Intent intent=new Intent(Intent.ACTION_CALL);
                                        intent.setData(Uri.parse("tel:" + number ));
                                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                            return;
                                        }
                                        startActivity(intent);
                                    }
                                });
                            }
                        });

                    }

                }
            });

        }

    }
}
