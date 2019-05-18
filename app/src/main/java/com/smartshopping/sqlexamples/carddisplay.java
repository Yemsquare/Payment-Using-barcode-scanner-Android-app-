package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class carddisplay extends AppCompatActivity {
    List<Cources> productList;

    //the recyclerview
    RecyclerView recyclerView;
   //ListView listCollege;
    ProgressBar proCollageList;
    Intent putamount=new Intent(this,Home.class);
    //List<Cources>value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carddisplay);
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        proCollageList= (ProgressBar) findViewById(R.id.carddisplaypro);
        proCollageList.setProgress(View.VISIBLE);
        //initializing the productlist
        productList = new ArrayList<>();
        //this method will fetch and parse json
        //to display it in recyclerview
        //loadProducts();
        showList();

    }
    private void showList(){
        proCollageList.setProgress(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, ("http://epagogic-apprehensi.000webhostapp.com/carddisplay.php"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try{
                            proCollageList.setProgress(View.INVISIBLE);
                        JSONObject obj=new JSONObject(s);
                        JSONArray array=obj.getJSONArray("bankcard");
                            for(int i=0;i<array.length();i++){
                                JSONObject Cardobj=array.getJSONObject(i);
                               // Cources c= new Cources(Cardobj.getString("Cources_bankname"),Cardobj.getString("Cources_amunt"));
                                productList.add(new Cources(Cardobj.getString("bank_name"),Cardobj.getInt("Amount")));
                                ListAdapter adapter=new ListAdapter(carddisplay.this,productList);
                                recyclerView.setAdapter(adapter);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(carddisplay.this,"Error", Toast.LENGTH_LONG).show();
                volleyError.printStackTrace();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

        //MyDBHandler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
        //MyDBHandler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

}
