package com.danielsapps.packbuddycontroller;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by daniel on 4/3/16.
 */
public class HostelLocations {
    double cbpLat = 59.336374;
    double cbpLong = 18.055137;
    float distanceToCbp=90;
    LocationManager lm;
    Activity a;
    TextView tv;
    boolean LocationServicePermissionAccepted=false;

    public HostelLocations(Activity a, TextView tv){
        this.a =a;
        this.tv=tv;
        lm = (android.location.LocationManager) this.a.getSystemService(Context.LOCATION_SERVICE);
        try {
            lm.requestLocationUpdates(android.location.LocationManager.NETWORK_PROVIDER, 0, 0, Ll);
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

    public android.location.LocationManager getLm() {
        return lm;
    }

    public ArrayList<String> checkIfNearBy(){
        ArrayList<String> al=null;
        if(distanceToCbp<200000){
            al = new ArrayList<String>();
            al.add("CityBackpackers hostel");
        }
        return al;
    }
}
