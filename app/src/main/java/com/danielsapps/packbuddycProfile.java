package com.danielsapps.packbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.danielsapps.model.EmailAndHostel;
import com.danielsapps.packbuddycontroller.CheckOut;
import com.danielsapps.packbuddycontroller.DataConversion;

public class Profile extends AppCompatActivity {
    String user_pref="main user preferences";
    SharedPreferences settings;
    String hostel;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        settings = getSharedPreferences(user_pref,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btn = (Button) findViewById(R.id.profileLogOut);
        hostel = getIntent().getStringExtra("hostel");
        email = settings.getString("email","empty");
        setSupportActionBar(toolbar);
        ImageView iv = (ImageView) findViewById(R.id.profileImage);
        iv.setRotation(270);
        iv.setImageBitmap(DataConversion.getImageAsBitmap(settings.getString("image", "empty")));
    }

    public void logOut(View v){
        settings.edit().clear().commit();
        checkOut(v);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void checkOut(View v){
        EmailAndHostel eah = new EmailAndHostel(email, hostel);
        CheckOut co = new CheckOut(eah);
        co.execute();
    }
}
