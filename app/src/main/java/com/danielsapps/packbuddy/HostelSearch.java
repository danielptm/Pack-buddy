package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class HostelSearch extends AppCompatActivity {
    TextView tv;
    TextView tvName;
    ImageView imageFromServer;
    ListView lv;
    LoadProfile lp;
    String email;
    HostelLocations hl;
    ProfileBean pfbFromServer;
    String hostelClickedOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        imageFromServer = (ImageView) findViewById(R.id.findHostelImage);
        imageFromServer.setRotation(270);
        email = (String) getIntent().getExtras().get("email");
        lp = new LoadProfile(email);
        hl = new HostelLocations(this, tv);
        lp.execute();
        try {
            pfbFromServer = lp.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        tvName.setText(pfbFromServer.getName());
        imageFromServer.setImageBitmap(DataConversion.getImageAsBitmap(pfbFromServer.getImg()));

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
        i.putExtra("password",pfbFromServer.getPassword());
        startActivity(i);
    }
}
