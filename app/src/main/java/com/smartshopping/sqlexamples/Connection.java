package com.smartshopping.sqlexamples;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by User on 26/07/2018.
 */

public class Connection extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    ProgressBar progressBar;
    Connection(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type1 = params[0];
        String register_url = "http://epagogic-apprehensi.000webhostapp.com/Register.php";
        if (type1.equals("Register")) {
            try {
                String user_name = params[1];
                String email_add = params[2];
                String teli=params[3];
               /* //String add=params[4];
                //String city=params[5];*/
                String passwrd=params[4];
                //String cpassword=params[7];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("email_add", "UTF-8") + "=" + URLEncoder.encode(email_add, "UTF-8") + "&" +
                        URLEncoder.encode("teli", "UTF-8") + "=" + URLEncoder.encode(teli, "UTF-8") + "&" +
                       /* URLEncoder.encode("add", "UTF-8") + "=" + URLEncoder.encode(add, "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&" +*/
                        URLEncoder.encode("passwrd", "UTF-8") + "=" + URLEncoder.encode(passwrd, "UTF-8"); //+ "&" +
                       // URLEncoder.encode("cpassword", "UTF-8") + "=" + URLEncoder.encode(cpassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Registration status");
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration success")) {
            Intent intent=new Intent(context,Login.class);
            context.startActivity(intent);
        }else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }


}