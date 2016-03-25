package com.danielsapps.packbuddycontroller;

import android.app.Activity;
import android.os.AsyncTask;

import com.danielsapps.model.ProfileBean;

/**
 * Created by daniel on 3/21/16.
 */
public class UpdateImage extends AsyncTask<String, String, String>{
    Activity a;
    ProfileBean pfb;

    public UpdateImage(ProfileBean pfb, Activity a){
        this.a =a;
        this.pfb=pfb;
    }


    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
