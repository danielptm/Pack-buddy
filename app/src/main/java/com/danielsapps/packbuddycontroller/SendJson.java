package com.danielsapps.packbuddycontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by daniel on 3/21/16.
 */
public class SendJson extends AsyncTask<Bitmap, Bitmap, Bitmap> {
    ProfileBean pfb;

    public SendJson(String name, String email, String homeCity, String password, Bitmap b){
        pfb = new ProfileBean(name, email, homeCity, password, convertTo64BitString(b) );

    }

    String testPath = "http://10.0.1.7:8181/Pack_pal/CreateProfile";
    String productionPath="http://37.139.14.185:8080/Pack_pal/CreateProfile";


    @Override
    protected Bitmap doInBackground(Bitmap... strings) {
        ProfileBean profileBeanToSend=null;
        JSONObject jobj = getProfileAsJson();
        Bitmap image64Bit;
        try {
            Gson gson = new Gson();
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.connect();
            Writer writer = new BufferedWriter( new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8" ));
            writer.write(jobj.toString());
            writer.close();
            InputStream is = httpConn.getInputStream();
            String x = convertStreamToString(is);
            profileBeanToSend = gson.fromJson(x, ProfileBean.class);
            httpConn.disconnect();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        image64Bit = getImageAsBitmap(profileBeanToSend.getImg());
        return image64Bit;
    }

    public JSONObject getProfileAsJson(){
        JSONObject jobj=null;
        try {

            jobj = new JSONObject();
            jobj.put("img", this.pfb.getImg());
            jobj.put("password", this.pfb.getPassword());
            jobj.put("homeCity", this.pfb.getHomeCity());
            jobj.put("email", this.pfb.getEmail());
            jobj.put("name", this.pfb.getName());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobj;
    }

    private String convertStreamToString(InputStream is){
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
    	StringBuilder total = new StringBuilder();
    	String line;
    	try {
			while ((line = r.readLine()) != null) {
			    total.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return total.toString();
    }

    public JSONObject convertToJsonObject(String str){
        JSONObject jobj = new JSONObject();
        try {
            jobj.getJSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jobj;
    }

    public String convertTo64BitString(Bitmap b){
        return Base64.encodeToString(getImageAsByte(b), Base64.DEFAULT);
    }

    public byte[] getImageAsByte(Bitmap img){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,os);
        return os.toByteArray();
    }

    public Bitmap getImageAsBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    public Bitmap getImageAsBitmap(String str64Bit){
        byte[] b = Base64.decode(str64Bit, Base64.DEFAULT);
        return getImageAsBitmap(b);

    }

}

