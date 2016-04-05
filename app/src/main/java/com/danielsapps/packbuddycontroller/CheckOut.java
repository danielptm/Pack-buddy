package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;

import com.danielsapps.model.EmailAndHostel;
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
 * Created by daniel on 4/4/16.
 */
public class CheckOut extends AsyncTask<Void, Void, Void> {
    String testPath = Paths.myPhone+"/CheckOut";
    EmailAndHostel eah;

    public CheckOut(EmailAndHostel eah){
        this.eah=eah;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Gson gson = new Gson();
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            String x = gson.toJson(this.eah);
            osw.write(x);
            osw.flush();
            osw.close();

            InputStream is = httpConn.getInputStream();
            DataConversion dc = new DataConversion();
            String jsonString = dc.convertStreamToString2(is);
            is.close();

            httpConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
