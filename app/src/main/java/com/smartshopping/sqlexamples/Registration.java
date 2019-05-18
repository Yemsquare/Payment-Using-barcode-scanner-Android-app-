package com.smartshopping.sqlexamples;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText username;
    TextInputEditText email1address;
    TextInputEditText phoneNo;
    //TextInputEditText address;
    //TextInputEditText city;
    TextInputEditText passwod;
    TextInputEditText cpass;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=(TextInputEditText) findViewById(R.id.Fullname);
        email1address=(TextInputEditText) findViewById(R.id.emailaddress);
        phoneNo= (TextInputEditText) findViewById(R.id.phonenumb);
       // address=(TextInputEditText) findViewById(R.id.address);
        //city=(TextInputEditText) findViewById(R.id.city);
        passwod= (TextInputEditText) findViewById(R.id.registerpassword);
        cpass= (TextInputEditText) findViewById(R.id.cpassword);
        btnregister= (Button) findViewById(R.id.register);
        btnregister.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String type1="Register";
                String usern=username.getText().toString().trim();
                String email=email1address.getText().toString().trim();
               String phone=phoneNo.getText().toString().trim();
                //String addrss=address.getText().toString().trim();
               // String citi=city.getText().toString().trim();
                String pass=passwod.getText().toString().trim();
                //String cpas=cpass.getText().toString().trim();
                Connection connection=new Connection(this);
                connection.execute(type1,usern,email,phone,pass);
                break;
        }
    }
}
