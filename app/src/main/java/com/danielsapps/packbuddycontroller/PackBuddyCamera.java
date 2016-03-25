package com.danielsapps.packbuddycontroller;

import android.hardware.Camera;
import android.os.AsyncTask;


/**
 * Created by daniel on 3/24/16.
 */
public class PackBuddyCamera extends AsyncTask<Void, Void, Void> {





    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    public Camera openFrontCamera(){
        Camera cam = null;
        Camera.CameraInfo ci = new Camera.CameraInfo();
        int nc = Camera.getNumberOfCameras();

        for(int i=0; i<nc; i++){
            Camera.getCameraInfo(i, ci);
            if(ci.facing==Camera.CameraInfo.CAMERA_FACING_FRONT){
                try{
                    cam = Camera.open(i);
                }catch(RuntimeException e){e.printStackTrace();}
            }
        }
        return cam;
    }


}
