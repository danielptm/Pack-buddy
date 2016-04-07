package com.danielsapps.packbuddy;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.danielsapps.packbuddycontroller.StartAppController;

/**
 * @author daniel
 */
public class MainActivity extends AppCompatActivity {
    String info="info";
    String user_pref="main user preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getPermission();
        Log.d(info, Integer.toString(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(userPrefsExist()){
            moveToHostelSearch();
        }
    }

    public void moveToCreateProfile(View view){
        Intent i = new Intent(this, TakePicture.class);
        startActivity(i);
    }

    public void moveToLogIn(View view){
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }

    public void moveToHostelSearch(){
        Intent i = new Intent(this, HostelSearch.class);
        startActivity(i);
    }

    public boolean userPrefsExist(){
        boolean prefsExist=false;
        SharedPreferences settings = getSharedPreferences(user_pref,0);
        if(settings.contains("name")){
            prefsExist=true;
        }
        return prefsExist;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent i){
//        switch(requestCode){
//            case 10:
//        }
//    }

    public void getPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 10);
        }
    }
}
