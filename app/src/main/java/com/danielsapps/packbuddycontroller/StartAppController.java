package com.danielsapps.packbuddycontroller;

import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.VideoView;

/**
 * Created by daniel on 4/6/16.
 * @author daniel
 */
public class StartAppController extends AsyncTask<Void, Void, Void> {

    VideoView vv;
    public StartAppController(VideoView vv){
        this.vv = vv;
    }


    @Override
    protected Void doInBackground(Void... params) {
        this.vv.start();
        return null;
    }
}
