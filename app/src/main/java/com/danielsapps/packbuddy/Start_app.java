package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Start_app extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);

    }


    public void moveToCreateProfile(View view){
        Intent i = new Intent(this, CreateProfile.class);
        startActivity(i);
    }
}

//TODO: Implement the createAProfile() function so that when the button is clicked the view changes to the createAProfile view



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
