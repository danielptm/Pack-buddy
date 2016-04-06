package com.danielsapps.packbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.danielsapps.packbuddycontroller.StartAppController;

/**
 * @author daniel
 */
public class MainActivity extends AppCompatActivity {
    String user_pref="main user preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
}
