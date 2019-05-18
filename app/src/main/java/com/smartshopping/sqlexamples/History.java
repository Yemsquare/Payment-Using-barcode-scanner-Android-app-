package com.smartshopping.sqlexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    private List<Items> ItemsList;
    private RecyclerView recycler;
    Bundle extras;
    Intent intent;
    ProgressBar histrpro;
    //public static final String TAG=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recycler = (RecyclerView) findViewById(R.id.historyrecycler);
        histrpro= (ProgressBar) findViewById(R.id.historypro);
        intent=new Intent(this,HistoryAdapter.class);
        extras=getIntent().getExtras();
        String barc= extras.getString("key");
        intent.putExtra("key2",barc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(layoutManager);
        ItemsList = new ArrayList<>();
        histrpro.setProgress(View.INVISIBLE);


        //
        //test bundle

        //Toast.makeText(getApplicationContext(),extras.getString("key"),Toast.LENGTH_LONG).show();
        // String bars=getIntent().getParcelableExtra("key");
        //Toast.makeText(getApplicationContext(),barc,Toast.LENGTH_LONG).show();
        //List();
    }

    private void List() {
        histrpro.setProgress(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(History.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ("http://epagogic-apprehensi.000webhostapp.com/Barcode.php"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String hist) {
                        histrpro.setProgress(View.INVISIBLE);
                        try {
                           /* Bundle extras = getIntent().getExtras();
                            String barc = extras.getString("key");
                            final Map<String, String> params = new HashMap<String, String>();
                            params.put("barcod", String.valueOf(barc));*/

                            JSONObject jsonObject=new JSONObject(hist);
                            JSONArray jsonArray = jsonObject.getJSONArray("barcode");
                            //JSONArray arrayhist=new JSONArray(hist);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject barcodeobj = jsonArray.getJSONObject(i);
                                Toast.makeText(getApplicationContext(), barcodeobj.getString("product_name")+"null", Toast.LENGTH_SHORT).show();
                                // Cources c= new Cources(Cardobj.getString("Cources_bankname"),Cardobj.getString("Cources_amunt"));
                               ItemsList.add(new Items(barcodeobj.getString("product_name"), barcodeobj.getInt("amount")));
                                HistoryAdapter historyAdapter;
                                historyAdapter = new HistoryAdapter(History.this, ItemsList);
                                recycler.setAdapter(historyAdapter);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(History.this, "Error", Toast.LENGTH_LONG).show();
                volleyError.printStackTrace();

            }
        }) {
              /*  @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("Content-Type:", "application/json");
                    return params;*/

          @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parameters=new HashMap<>();

               //int barc= Integer.parseInt(extras.getString("key"));
               parameters.put("barcod",extras.getString("key"));
                //parameters.put("password",passwordupdate.getText().toString().trim());
               //parameters.put("confirm",confirmpass.getText().toString().trim());
                return parameters;
            }
        };
        //Bundle extras=getIntent().getExtras();
        //String barc= extras.getString("key");
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        //Toast.makeText(getApplicationContext(),barc,Toast.LENGTH_LONG).show();
        //Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    protected void onResume() {
        List();
        super.onResume();
    }
}