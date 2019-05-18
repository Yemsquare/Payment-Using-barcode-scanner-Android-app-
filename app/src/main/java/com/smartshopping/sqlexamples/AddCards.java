package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCards extends AppCompatActivity implements View.OnClickListener {

    EditText bankname;
    EditText cardno;
    EditText Amount;
    Button btnadd;
    Button display;
    TextView barcs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);

        bankname= (EditText) findViewById(R.id.issubank);
        cardno= (EditText) findViewById(R.id.cardno);
        Amount= (EditText) findViewById(R.id.amnt);
        btnadd= (Button) findViewById(R.id.addbtn);
        display=(Button) findViewById(R.id.display);
        display.setOnClickListener(this);
        btnadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.addbtn:
             String take="cardregistration";
             String bank=bankname.getText().toString();
             String cardn=cardno.getText().toString();
             String amt=Amount.getText().toString();
             cards crd=new cards(this);
             crd.execute(take,bank,cardn,amt);
             break;
         case  R.id.display:
             Intent intnt=new Intent(this, carddisplay.class);
             startActivity(intnt);

     }
    }
}
