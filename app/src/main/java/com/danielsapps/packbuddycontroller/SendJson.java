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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import android.util.Log;


/**
 * Created by daniel on 3/21/16.
 */
public class SendJson extends AsyncTask<Bitmap, Bitmap, Bitmap> {
    ProfileBean pfb;
    String info="infoTag";

    public SendJson(String name, String email, String homeCity, String password, Bitmap b){
        pfb = new ProfileBean(name, email, homeCity, password, convertTo64BitString(b) );

    }

    //10.0.1.7
    //192.168.156.45
    //192.168.156.45
    //192.168.156.45
    //10.255.228.211
    String testPath = "http://10.255.228.211:8181/Pack_pal/CreateProfile";
    String productionPath="http://37.139.14.185:8080/Pack_pal/CreateProfile";
    String jobpfb;


    @Override
    protected Bitmap doInBackground(Bitmap... strings) {

        try {
            jobpfb = getJsonStringWithGson(pfb);
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);


            Log.i(info, String.valueOf(jobpfb.length()));
            osw.write(jobpfb.toString());



            osw.flush();
            osw.close();

            InputStream is = httpConn.getInputStream();
            String x = convertStreamToString(is);
            is.close();

            httpConn.disconnect();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getJsonStringWithGson(ProfileBean pfb){
        Gson gson=null;
        gson = new Gson();
        String x = gson.toJson(pfb, ProfileBean.class);
        return x;
    }

    private String convertStreamToString(InputStream is){
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
    	StringBuilder total = new StringBuilder();
    	String line;
    	try {
			while ((line = r.readLine()) != null) {
//                Log.i(info, line);
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

    public String getJobpfb() {
        return jobpfb;
    }
}

