package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;

import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by daniel on 3/28/16.
 * @author daniel
 */
public class GetProfileFromDb extends AsyncTask<ProfileBean, ProfileBean, ProfileBean> {
    String email;
    String password;

    public GetProfileFromDb(String email, String password){
        this.email=email;
        this.password = password;
    }
    //192.168.156.49//10.0.1.7
    String testPath = "http://192.168.156.49:8181/Pack_pal/getProfile";
    String productionPath="http://37.139.14.185:8080/Pack_pal/getProfile";
    ProfileBean beanToReceive;
    ProfileBean pfb;

    @Override
    protected ProfileBean doInBackground(ProfileBean... params) {
        Gson gson = new Gson();
        URL url = null;
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("email", this.email);
            jobj.put("password", this.password);
            url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            Writer writer = new BufferedWriter( new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8" ));
            writer.write(jobj.toString());
            writer.flush();
            writer.close();
            InputStream is = httpConn.getInputStream();
            String x = convertStreamToString(is);
            beanToReceive = gson.fromJson(x, ProfileBean.class);
            pfb = gson.fromJson(x, ProfileBean.class);
            System.out.println("Name: "+pfb.getName()+"Email: "+pfb.getEmail()+"password: "+pfb.getPassword()+"homecity: "+pfb.getHomeCity()+"image: "+pfb.getImg());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pfb;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return total.toString();
    }
//    public JsonObject getProfileAsJson(ProfileBean pfb){
//        JsonObject jobj=null;
//        jobj = new JsonObject();
//        jobj.addProperty("img", pfb.getImg() );
//        jobj.addProperty("password", pfb.getPassword());
//        jobj.addProperty("homeCity", pfb.getHomeCity());
//        jobj.addProperty("email", pfb.getEmail());
//        jobj.addProperty("name", pfb.getName());
//        return jobj;
//    }
}
