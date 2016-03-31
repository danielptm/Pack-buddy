package com.danielsapps.packbuddycontroller;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by daniel on 3/31/16.
 */
public class HostelSearchController extends AsyncTask<String, String, String>{
    LocationManager lm;
    Activity a;
    double cbpLat = 59.336374;
    double cbpLong = 18.055137;
    float distanceToCbp=90;
    TextView tv;
    String testPath = "http://192.168.156.45:8181/Pack_pal/CreateProfile";

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(testPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            osw.write("checkin");

            osw.flush();
            osw.close();

            httpConn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }


    boolean LocationServicePermissionAccepted=false;

    public HostelSearchController(Activity a, TextView tv){
        this.tv = tv;
        this.a=a;
        lm = (LocationManager) this.a.getSystemService(Context.LOCATION_SERVICE);

        try {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, Ll);
            LocationServicePermissionAccepted=true;
        }catch(SecurityException e){LocationServicePermissionAccepted=false;}
    }

    LocationListener Ll = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            Location cbp = new Location("cbp");
            cbp.setLatitude(cbpLat);
            cbp.setLongitude(cbpLong);
            distanceToCbp = location.distanceTo(cbp);
            tv.setText(Float.toString(distanceToCbp));

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public LocationManager getLm() {
        return lm;
    }

    public boolean checkIfNearBy(){
        if(distanceToCbp<5000){
            return true;
        }
        else{
            return false;
        }
    }
}
