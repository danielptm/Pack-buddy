package com.danielsapps.packbuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;


import com.danielsapps.model.DbHandler;
import com.danielsapps.packbuddycontroller.PackBuddyCamera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePicture extends AppCompatActivity {
    Camera cam;
    PackBuddyCamera pfc;
    SurfaceView sv;
    SurfaceHolder svHolder;
    ImageView iv;
    String info = "info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_picture);
        sv = (SurfaceView) findViewById(R.id.surfaceView);
        svHolder = sv.getHolder();
        iv = (ImageView) findViewById(R.id.ImageTaken);
        svHolder.addCallback(cb);
        svHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        pfc = new PackBuddyCamera();


    }

    @Override
    protected void onPause(){
        super.onPause();
        getCam().stopPreview();
        getCam().release();

    }

    SurfaceHolder.Callback cb = new SurfaceHolder.Callback(){

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    public void takePicture(View view){

        cam.takePicture(null, null, picCallBack);
    }

    public void startPreview(){
        try {
            cam = pfc.openFrontCamera();
            Camera.Parameters parameters = cam.getParameters();
            parameters.setPictureSize(50, 50);
            parameters.setJpegQuality(10);
            cam.setParameters(parameters);
            cam.setDisplayOrientation(90);
            cam.setPreviewDisplay(sv.getHolder());
            cam.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Camera.PictureCallback picCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            savePictureAndGoToCreateProfile(data);
        }
    };


    public void savePictureAndGoToCreateProfile(byte[] imageData){
        String fileName = "picture";
        FileOutputStream os;
        File f = new File(getFilesDir(),fileName);
        try {
            os = openFileOutput(fileName, Context.MODE_PRIVATE);
            os.write(imageData, 0, imageData.length);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(info, String.valueOf(getFilesDir()));
        Log.d(info, "line 116");
        Intent i = new Intent(this, CreateProfile.class);

        i.putExtra("image", "picture");
        startActivity(i);
        finish();

    }

    public Camera getCam(){
        return cam;
    }
}