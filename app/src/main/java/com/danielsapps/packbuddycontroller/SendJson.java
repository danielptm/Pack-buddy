package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by daniel on 3/21/16.
 */
public class SendJson extends AsyncTask<Void, Void, Void> {
    ProfileBean pfb;
    String urlAddress="http://37.139.14.185:8080/Pack_pal/CreateProfile";

    public SendJson(ProfileBean pfb){
        this.pfb=pfb;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        System.out.println("line 26");
        JSONObject jobj = getProfileAsJson();

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.connect();

            Writer writer = new BufferedWriter( new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8" ));
            writer.write(jobj.toString());
            writer.close();


            InputStream is = httpConn.getInputStream();
            System.out.println(convertStreamToString(is));

            httpConn.disconnect();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        System.out.println("end of sendJson");
        return null;
    }

    public JSONObject getProfileAsJson(){
        JSONObject jobj=null;
        try {
            jobj = new JSONObject();
            jobj.put("name", this.pfb.getName());
            jobj.put("email", this.pfb.getEmail());
            jobj.put("homeCity",this.pfb.getHomeCity() );
            jobj.put("password", this.pfb.getPassword());
//            jobj.put("img",this.pfb.getImg() );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobj;
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
