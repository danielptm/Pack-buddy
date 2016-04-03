package com.danielsapps.packbuddycontroller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.danielsapps.model.ProfileBean;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;


/**
 * Created by daniel on 3/21/16.
 */
public class SendJson extends AsyncTask<String, String, String> {
    ProfileBean pfb;
    String info="infoTag";
    Activity a;
    String message;

    public SendJson(String name, String email, String homeCity, String password, Bitmap b){
        pfb = new ProfileBean(name, email, homeCity, password, DataConversion.convertTo64BitString(b) );

    }

    //10.0.1.7
    //192.168.156.45
    //192.168.156.45
    //192.168.156.45
    //10.255.228.211
    //192.168.43.26
    String testPath = Paths.myPhone+"/CreateProfile";
    String productionPath="http://37.139.14.185:8080/Pack_pal/CreateProfile";
    String jobpfb;


    @Override
    protected String doInBackground(String... strings) {

        try {
            jobpfb = DataConversion.getJsonStringWithGson(pfb);
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
            os.flush();
            os.close();
            InputStream is = httpConn.getInputStream();
            message = DataConversion.convertStreamToString(is);
            is.close();
            httpConn.disconnect();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return message;
    }
}

