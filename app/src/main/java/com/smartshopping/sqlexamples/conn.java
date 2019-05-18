package com.smartshopping.sqlexamples;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by User on 04/10/2018.
 */

public class conn {

    public static Object connect(String urlAddress){
        urlAddress="http:/192.168.43.11/Android/login.php";
        try{
            URL url=new URL(urlAddress);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            //Set conn properties
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoOutput(true);
            return connection;

        } catch (ProtocolException e) {
            e.printStackTrace();
            return "error" +e.getMessage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return "error" +e.getMessage();
        }
        return urlAddress;
    }
}
