package com.danielsapps.packbuddycontroller;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by daniel on 3/24/16.
 * @author daniel
 * @author daniel
 */
public class PackBuddyCamera extends AsyncTask<Void, Void, Void> {
    String info2="info";





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
                Log.d(info2, String.valueOf(ci.facing));
                try{
                    cam = Camera.open(i);

                }catch(RuntimeException e){e.printStackTrace();}
            }
        }
        return cam;
    }


}
