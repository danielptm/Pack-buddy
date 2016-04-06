package com.danielsapps.packbuddycontroller;

import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

import com.danielsapps.model.EmailAndHostel;
import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 4/4/16.
 * @author daniel
 */
public class LoadProfileAndGuests extends AsyncTask<ArrayList<ProfileBean>, ArrayList<ProfileBean>, ArrayList<ProfileBean>> {

    String testPath = Paths.myPhone+"/loadProfileAndGuests";
    String email;
    String hostelName=null;
    Gson gson;
    ArrayList<ProfileBean> alpfb;
    int pageToLoad;

    public LoadProfileAndGuests(String email, String hostelName, int pageToLoad ){
        this.email = email;
        this.hostelName=hostelName;
        this.pageToLoad=pageToLoad;
    }
    @Override
    protected ArrayList<ProfileBean> doInBackground(ArrayList<ProfileBean>... params) {
        try {
            gson = new Gson();
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            EmailAndHostel eah = new EmailAndHostel(this.email, this.hostelName, this.pageToLoad);
            String x = gson.toJson(eah);

            osw.write(x);
            osw.flush();
            osw.close();

            InputStream is = httpConn.getInputStream();
            DataConversion dc = new DataConversion();
            String jsonString = dc.convertStreamToString2(is);
            is.close();

            alpfb = gson.fromJson(jsonString, new TypeToken<List<ProfileBean>>(){}.getType());

            httpConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alpfb;
    }
}
