package com.danielsapps.packbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.danielsapps.fragments.ListViewAdapter;
import com.danielsapps.model.EmailAndHostel;
import com.danielsapps.model.EmailAndPasswordBean;
import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddycontroller.CheckIn;
import com.danielsapps.packbuddycontroller.CheckOut;
import com.danielsapps.packbuddycontroller.DataConversion;
import com.danielsapps.packbuddycontroller.HostelLocations;
import com.danielsapps.packbuddycontroller.LoadProfile;
import com.danielsapps.packbuddycontroller.LoadProfileAndGuests;
import com.danielsapps.packbuddycontroller.LoadUserProfileFromServer;
import com.danielsapps.packbuddycontroller.SendEmailAndPassword;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomePage extends AppCompatActivity {
    ArrayList<ProfileBean> pfbs;
    ArrayList<ProfileBean> editedList;
    String password;
    String hostel;
    String email;
    ImageView iv;
    TextView tv;
    TextView tv2;
    ListView lv;
    HostelLocations hl;
    ProfileBean user;
    String info="info";
    int pageToLoad=0;
    String homePageLog="Homepage";

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
        pfbs = loadCheckedIn();
        user = pfbs.get(0);
        iv.setImageBitmap(DataConversion.getImageAsBitmap(user.getImg()));
        iv.setRotation(270);
        tv.setText(hostel);
        loadCheckedIns();

    }

    public ArrayList<ProfileBean> loadCheckedIn(){
        ArrayList<ProfileBean> alpfbs=null;
        LoadProfileAndGuests lp = new LoadProfileAndGuests(email,hostel, pageToLoad);
        pageToLoad+=3; //Increments so that the next time the function is called the next 3 columns will be retrieved.
        Log.d(homePageLog, "Right before lp.execute");
        lp.execute();
        try {
            alpfbs = lp.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(homePageLog,"Right before the for loop");
        Log.d(homePageLog, String.valueOf(alpfbs.size()));
        for(ProfileBean pfb: alpfbs){
            Log.d(homePageLog, pfb.getName());
        }
        return alpfbs;
    }

    public void checkOut(View v){
        EmailAndHostel eah = new EmailAndHostel(email, hostel);
        CheckOut co = new CheckOut(eah);
        co.execute();
        lv.setAdapter(null);

    }

    public void loadCheckedIns(){
        editedList = new ArrayList<ProfileBean>();

        for(ProfileBean pfb: pfbs){
            if(! pfb.getEmail().equals(user.getEmail())){
                editedList.add(pfb);
            }
        }
        ListAdapter lad = new ListViewAdapter(this, editedList);
        lv.setAdapter(lad);
    }
}
