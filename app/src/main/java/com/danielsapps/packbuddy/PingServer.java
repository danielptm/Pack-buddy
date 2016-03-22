package com.danielsapps.packbuddy;

import android.os.Bundle;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.danielsapps.packbuddycontroller.GetJson;
import android.os.Message;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class PingServer extends AppCompatActivity {

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            TextView tv = (TextView) findViewById(R.id.pingServerText);
            tv.setText(msg.toString());
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

    }

    public void pingServer(View target){
        GetJson gj = new GetJson();
        String x=null;
        gj.execute();
        try {
            x= gj.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Message msg = Message.obtain();
        msg.obj = x;
        handler.sendMessage(msg);
    }
}


//TODO: 3 svåraste delarna

//TODO: Create a profile and load it into the remote db.
//TODO: Log into the profile with the user name and the data, and retrieve their information.

//TODO: Once logged in, they can do a search to see if there are hostels nearby
//TODO: 2) Load a list of hostels in the area based up on a google search for business with the names hostel or hotel.
//TODO: Use googleplaces api https://developers.google.com/places/android-api/signup#release-cert
//TODO: Get the gps coordinates of cbp, and if the person is within 50 m of CBP, then they can check in.

//TODO: 3) Secondary, Load an image to the database as part of user object.
//TODO: This is set and got together as part of the user profile.


//TODO: 4) Within the app take a picture of the user and save it somehow so that it can be uploaded to the db with the rest of the user info.
