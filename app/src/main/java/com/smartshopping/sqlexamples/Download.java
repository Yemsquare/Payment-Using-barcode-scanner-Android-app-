package com.smartshopping.sqlexamples;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by User on 04/10/2018.
 */

public class Download extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    ListView lv;
    //Spinner spinner;
    ProgressBar pb;
    public Download(Context c,String urlAddress,ListView lv){
        this.c=c;
        this.urlAddress=urlAddress;
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
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }
    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        if(jsonData.startsWith("Error")){
            Toast.makeText(c,"Unsuccessful"+jsonData,Toast.LENGTH_SHORT).show();
        }
    }
    private String downloadData(){
        Object connection=conn.connect(urlAddress);
        if(connection.toString().startsWith("Error")){
            return connection.toString();
        }
        try {
            HttpURLConnection con=(HttpURLConnection) connection;
            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader bR=new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData=new StringBuffer();
            while((line= bR.readLine()) !=null){
                jsonData.append(line+"\n");
            }
            bR.close();
            is.close();
            return jsonData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error"+e.getMessage();
        }

    }
}
