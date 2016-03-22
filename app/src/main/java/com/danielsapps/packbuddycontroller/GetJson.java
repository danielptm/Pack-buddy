package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by daniel on 3/20/16.
 */
public class GetJson extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        return getResultFromServlet(callServlet());
    }

    private String getResultFromServlet(InputStream is){
        JSONObject jo=null;
        if(is!=null){
            try {
                jo = new JSONObject(convertStreamToString(is));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jo.toString();
    }
    private InputStream callServlet(){
        InputStream is=null;
        try {
            URL url = new URL("http://37.139.14.185:8080/WebDbTest/sayHi");
            try {

                URLConnection con = url.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) con;
                httpConn.setRequestMethod("POST");
                httpConn.setDoInput(true);
                httpConn.connect();
                is = httpConn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return is;
    }

    private String convertStreamToString(InputStream is){
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder jsonString =  new StringBuilder();

        String line=null;
        try {
            while((line = br.readLine())!=null){
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString.toString();
    }

}
