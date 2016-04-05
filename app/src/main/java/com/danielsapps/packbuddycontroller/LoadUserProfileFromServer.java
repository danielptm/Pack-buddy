package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;

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
 * Returns a ProfileBean from the server.
 */
public class LoadUserProfileFromServer extends AsyncTask<ProfileBean, ProfileBean, ProfileBean> {
    String testPath = Paths.myPhone+"/loadProfileAndGuests";
    String email;
    public LoadUserProfileFromServer(String email){
        this.email=email;
    }
    @Override
    protected ProfileBean doInBackground(ProfileBean... params) {
        ProfileBean pfb=null;
        Gson gson = new Gson();
        try {
            URL url = new URL(testPath);
            HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
            httpconn.setDoOutput(true);
            httpconn.setRequestMethod("POST");
            httpconn.connect();
            OutputStream os = httpconn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);


            osw.write(this.email);
            osw.flush();
            osw.close();

            InputStream is = httpconn.getInputStream();
            DataConversion dtc = new DataConversion();
            String jsonString = dtc.convertStreamToString(is);
            pfb = gson.fromJson(jsonString, ProfileBean.class);

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
