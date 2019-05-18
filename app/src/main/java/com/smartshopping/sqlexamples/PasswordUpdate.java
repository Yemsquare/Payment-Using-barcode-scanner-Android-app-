package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PasswordUpdate extends AppCompatActivity implements View.OnClickListener {
    private EditText emailupdate;
    private EditText passwordupdate;
    //private EditText confirmpass;
    ProgressBar progressBar;
    public static final String updateaccountURL="http://epagogic-apprehensi.000webhostapp.com/accountupdate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update);
        emailupdate= (EditText) findViewById(R.id.email_edtext);
        passwordupdate= (EditText) findViewById(R.id.paswrd);
        //confirmpass=findViewById(R.id.confpass);
        progressBar= (ProgressBar) findViewById(R.id.progressBar3);
        Button button= (Button) findViewById(R.id.btn_confirm);
        button.setOnClickListener(this);
        progressBar.setProgress(View.INVISIBLE);
       // final String emailupdate1=emailupdate.getText().toString();
       // final String passwordupdate1=passwordupdate.getText().toString();
        //final String confirmpass2=confirmpass.getText().toString();
    }
    private void updateaccount(){
        progressBar.setProgress(View.VISIBLE);
        RequestQueue queue= Volley.newRequestQueue(PasswordUpdate.this);
        String response=null;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, updateaccountURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setProgress(View.INVISIBLE);
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parameters=new HashMap<>();
                parameters.put("email",emailupdate.getText().toString().trim());
                parameters.put("password",passwordupdate.getText().toString().trim());
                //parameters.put("confirm",confirmpass.getText().toString().trim());
                return parameters;
            }
        };stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        updateaccount();
        Intent gotologin=new Intent(this,Login.class);
        startActivity(gotologin);
    }
}
