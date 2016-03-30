package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }


    public void moveToCreateProfile(View view){
        Intent i = new Intent(this, TakePicture.class);
        startActivity(i);
    }
}
