package com.danielsapps.packbuddycontroller;

import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by daniel on 3/31/16.
 * @author daniel
 */
public class LoadProfile extends AsyncTask<ProfileBean, ProfileBean, ProfileBean>{
    String jsString="jsString";
    LocationManager lm;
    String testPath = Paths.myPhone+"/CheckIn";
    String email;
    String hostelName=null;
    Gson gson;
    ProfileBean pfb;

    public LoadProfile(String email ){
        this.email = email;
    }


    @Override
    protected ProfileBean doInBackground(ProfileBean... params) {
        try {
            gson = new Gson();
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            osw.write(this.email);
            osw.flush();
            osw.close();


            InputStream is = httpConn.getInputStream();
            DataConversion dc = new DataConversion();
            String jsonString = dc.convertStreamToString2(is);
            is.close();

            pfb = gson.fromJson(jsonString, ProfileBean.class);

            httpConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pfb;
    }
}
