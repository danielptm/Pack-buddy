package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;
import android.util.Log;

import com.danielsapps.model.EmailAndPasswordBean;
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
 * Created by daniel on 4/2/16.
 * @author daniel
 */
public class SendEmailAndPassword extends AsyncTask<Boolean, Boolean, Boolean>{
    Boolean validUserPasswordAndEmail;
    EmailAndPasswordBean epb;
    String testPath = Paths.atSchool+"/login";
    String productionPath="http://37.139.14.185:8080/Pack_pal/CreateProfile";
    String jsonepb;
    String info="info";
    private static ProfileBean pfb=null;

    public SendEmailAndPassword(EmailAndPasswordBean epb){
        this.epb=epb;
    }

    @Override
    protected Boolean doInBackground(Boolean... params) {
        try {

            jsonepb = DataConversion.getJsonStringWithGson(this.epb);
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("ContentType", "application/json");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(jsonepb);
            osw.flush();
            osw.close();

            InputStream is = httpConn.getInputStream();
            String pfbJson = DataConversion.convertStreamToString(is);
            Log.d(info, pfbJson);
            is.close();
            validUserPasswordAndEmail = checkIfValidUser(pfbJson);
            httpConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validUserPasswordAndEmail;
    }

    /**
     * Returns the profile bean retrieved during the loging process, check to make sure that this is not null.
     * @returns ProfileBean
     */
    public Boolean checkIfValidUser(String pfbJson){
        Gson gson = new Gson();
        if(! pfbJson.equals("False")){
            pfb = gson.fromJson(pfbJson, ProfileBean.class);
            return true;
        }
        else{
            return false;
        }
    }
    public static ProfileBean getPfb(){
        return pfb;
    }
}

