package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.danielsapps.model.ProfileBean;

/**
 * Created by daniel on 3/28/16.
 */
public class GetProfileData extends AsyncTask<ProfileBean, ProfileBean, ProfileBean> {


    @Override
    protected ProfileBean doInBackground(ProfileBean... params) {
        return null;
    }
}


//Get a row of data from the db, put it into a profile bean and display it in this view.