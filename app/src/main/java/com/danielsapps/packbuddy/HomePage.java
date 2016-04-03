package com.danielsapps.packbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.danielsapps.model.EmailAndPasswordBean;
import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddycontroller.CheckIn;
import com.danielsapps.packbuddycontroller.DataConversion;
import com.danielsapps.packbuddycontroller.LoadProfile;
import com.danielsapps.packbuddycontroller.SendEmailAndPassword;

import java.util.concurrent.ExecutionException;

public class HomePage extends AppCompatActivity {
    ProfileBean pfb;
    String password;
    String hostel;
    String email;
    ImageView iv;
    TextView tv;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        hostel = getIntent().getStringExtra("hostel");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        iv = (ImageView) findViewById(R.id.checkInImage);
        tv = (TextView) findViewById(R.id.CheckInHostelName);
        lv = (ListView) findViewById(R.id.CheckInHostelListview);
        LoadProfile lp = new LoadProfile(email);
        lp.execute();
        try {
            pfb = lp.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        iv.setImageBitmap(DataConversion.getImageAsBitmap(pfb.getImg()));
        tv.setText(hostel);


    }

}
