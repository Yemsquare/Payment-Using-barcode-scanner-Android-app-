package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private CheckBox checkBox;
    private TextView textView;
    TextInputEditText userna;
    TextInputEditText passwor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn= (Button) findViewById(R.id.loginBtn);
        checkBox=(CheckBox) findViewById(R.id.checkboxbtn);
        textView= (TextView) findViewById(R.id.reghere);
        userna= (TextInputEditText) findViewById(R.id.user);
        passwor= (TextInputEditText) findViewById(R.id.password);
        btn.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
               String username = userna.getText().toString();
                String password = passwor.getText().toString();
                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, username, password);
                //Intent i = new Intent(this, MainActivity.class);
                //startActivity(i);
                break;
            case R.id.reghere:
                Intent intent1 = new Intent(this, Registration.class);
                startActivity(intent1);
                break;
        }
    }

}