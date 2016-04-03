package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;
import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by daniel on 4/3/16.
 */
public class CheckIn extends AsyncTask<ProfileBean, ProfileBean, ProfileBean> {
    String email;
    ProfileBean pfb=null;
    String testPath = Paths.myPhone+"/CheckIn";
    public CheckIn(String email){

        this.email = email;
    }

    @Override
    protected ProfileBean doInBackground(ProfileBean... params) {
        Gson gson = new Gson();


        try {
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();


            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            String jsobj = gson.toJson(this.email);
            osw.write(jsobj);
            osw.flush();
            osw.close();

            InputStream is = httpConn.getInputStream();
            String x = DataConversion.convertStreamToString(is);

            pfb = gson.fromJson(x, ProfileBean.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pfb;
    }
}
