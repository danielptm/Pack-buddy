package com.danielsapps.packbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddycontroller.CheckIn;
import com.danielsapps.packbuddycontroller.DataConversion;
import com.danielsapps.packbuddycontroller.HostelLocations;
import com.danielsapps.packbuddycontroller.LoadProfile;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author daniel
 */
public class HostelSearch extends AppCompatActivity {
    TextView tv;
    TextView tvName;
    ImageView iv;
    ListView lv;
    LoadProfile lp;
    String email;
    HostelLocations hl;
    ProfileBean settingsPfb;
    ProfileBean pfbFromServer;
    String hostelClickedOn;
    String user_pref="main user preferences";
    String password;
    Toolbar toolbar;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_search);
        loadReferences();
        if(prefsExist()){
            loadPrefs();
            tvName.setText(settingsPfb.getName());
            iv.setImageBitmap(DataConversion.getImageAsBitmap(settingsPfb.getImg()));
            iv.setRotation(270);
        }else{
            email = getIntent().getStringExtra("email");
            loadDataFromServer();}
        hl = new HostelLocations(this, tv);

    }

    //Do a search for hostels within 100 meters of the user.
    public void checkInToHostel(View v){
        ArrayList<String> hostels=hl.checkIfNearBy();
        if(hostels!=null){
            ListAdapter lad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hostels);
            lv.setAdapter(lad);

        }
    }
    public void getHostelAndMoveToHomePage(String h){
        Intent i = new Intent(this, HomePage.class );
        i.putExtra("email", email);
        i.putExtra("hostel", h);
        i.putExtra("password",password);
        startActivity(i);
    }

    public boolean prefsExist(){
        boolean prefsExist=false;
        settings=null;
        settings = getSharedPreferences(user_pref,0);
        if(settings.contains("name")){
            prefsExist=true;
        }
        return prefsExist;
    }

    public void loadPrefs(){
        settings = null;
        settings = getSharedPreferences(user_pref, 0);
        settingsPfb = new ProfileBean(
                settings.getString("name", "empty"),
                settings.getString("email", "empty"),
                settings.getString("homeCity", "empty"),
                settings.getString("password", "empty"),
                settings.getString("image", "empty")
        );
        email = settingsPfb.getEmail();
        password=settingsPfb.getPassword();
    }

    public void loadReferences(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pack pal");
        ImageButton ib = (ImageButton) findViewById(R.id.user);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hostelClickedOn = (String) parent.getItemAtPosition(position);
                getHostelAndMoveToHomePage(hostelClickedOn);
            }
        });
        tv = (TextView) findViewById(R.id.distanceToCbp);
        tvName = (TextView) findViewById(R.id.hostelSearchProfileName);
        iv = (ImageView) findViewById(R.id.findHostelImage);
    }

    public void goToProfile(View v){
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

    public void loadDataFromServer(){
        lp = new LoadProfile(email);
        lp.execute();
        try {
            pfbFromServer = lp.get();
            iv.setImageBitmap(DataConversion.getImageAsBitmap(pfbFromServer.getImg()));
            password = pfbFromServer.getPassword();
            createUserPreferences(pfbFromServer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void createUserPreferences(ProfileBean pfb){
        settings=null;
        settings = getSharedPreferences(user_pref,0);
        SharedPreferences.Editor e = settings.edit();
        e.putString("name", pfb.getName());
        e.putString("email", pfb.getEmail());
        e.putString("password", pfb.getPassword());
        e.putString("homeCity", pfb.getHomeCity());
        e.putString("image", pfb.getImg());
        e.commit();
    }
}
