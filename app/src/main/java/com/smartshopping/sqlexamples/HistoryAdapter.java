package com.smartshopping.sqlexamples;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by User on 02/11/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemsViewHolder> {
    private Context contx;
    private List<Items> ItemsList;
    Bundle extras1;
    Intent intent;
    public HistoryAdapter(Context contx, List<Items> itemsList) {
        this.contx = contx;
        ItemsList = itemsList;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contx);
        View view = inflater.inflate(R.layout.item_list,parent,false);
       // intent=((Activity)contx).getIntent();
        //Toast.makeText(contx.getApplicationContext(),intent.getStringExtra("key2"),Toast.LENGTH_LONG).show();
       return new ItemsViewHolder(view);
    }
    public void purchase(){
        String response=null;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ("http://epagogic-apprehensi.000webhostapp.com/Purchase.php"),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //Toast.makeText(contx.getApplicationContext(),extras1.getString("key2"),Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(contx,volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){/*
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters=new HashMap<>();
                parameters.put("itemvalue", String.valueOf(extras1.getString("key2")));

                return parameters;
            }*/
        };
        Volley.newRequestQueue(contx.getApplicationContext()).add(stringRequest);
        //Toast.makeText(contx.getApplicationContext(),intent.getSt,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Items items=ItemsList.get(position);
        holder.prodname.setText(items.getProduct_name());
        holder.prodamount.setText(String.valueOf(items.getItem_amount()));
        holder.btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(contx);
                builder.setTitle("Payment Confirmation");
                builder.setMessage("Are you sure you want to take action");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      // RequestQueue queue= Volley.newRequestQueue(HistoryAdapter.this);
                        purchase();
                    }
                });
                builder.setNegativeButton("No",null);
                builder.create();
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemsList.size();
    }

     class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView prodname,prodamount;
         Button btnpurchase;
        public ItemsViewHolder(View itemView) {
            super(itemView);
            prodname=itemView.findViewById(R.id.prod_name);
            prodamount=itemView.findViewById(R.id.prod_amount);
            btnpurchase=itemView.findViewById(R.id.submit_prod);
        }
    }
}
