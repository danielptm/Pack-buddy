package com.danielsapps.packbuddycontroller;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.danielsapps.model.DbHandler;

/**
 * Created by daniel on 3/27/16.
 */
public class GetProfilePictureFromDb extends AsyncTask<Bitmap, Bitmap, Bitmap> {
    DbHandler dbh;
    byte[] b;
    Bitmap bm;

    public GetProfilePictureFromDb(DbHandler dbh){
        this.dbh = dbh;
    }


    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        byte[] b =  dbh.selectPicture();
        bm = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bm;
    }
}
