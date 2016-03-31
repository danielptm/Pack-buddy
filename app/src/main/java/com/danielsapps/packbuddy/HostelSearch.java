package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.danielsapps.packbuddycontroller.HostelSearchController;


public class HostelSearch extends AppCompatActivity {
    TextView tv;
    HostelSearchController hs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = (TextView) findViewById(R.id.distanceToCbp);
        String email = (String) getIntent().getExtras().get("email");
        hs = new HostelSearchController(this, tv);



    }

    public void searchForHostels(){
        if(hs.checkIfNearBy()){
            hs.execute();
        }
    }
}
