package com.smartshopping.sqlexamples;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 04/10/2018.
 */
/*
public class Dataparse extends AsyncTask<Void,Void,Boolean> {
    Context c;
    //String urlAddress;
    ListView lv;
    String jsonData;
    //Spinner spinner;
    ProgressBar pb;
    ArrayList<String> cardbank=new ArrayList<>();
    public Dataparse(Context c){
        this.c=c;
        this.jsonData=jsonData;
        this.lv=lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pb=new ProgressBar(c);
        pb.setProgress(15000);
        pb.isShown();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
    private Boolean parseData() {
            try {
                JSONArray jsonArray=new JSONArray(jsonData);
                JSONObject jsonObject;
                cardbank.clear();
                for (int i=0;i< jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);
                String bankname=jsonObject.getString("bank name");
                String cardno=jsonObject.getString("Card no");
                String Amount=jsonObject.getString("Amount");
                cardbank.add(bankname);
                cardbank.add(cardno);
                cardbank.add(Amount);
            }
        } catch (JSONException e) {
                e.printStackTrace();
            }
        return false;
    }
}
*/