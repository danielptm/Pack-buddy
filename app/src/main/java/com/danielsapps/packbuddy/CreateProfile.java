package com.danielsapps.packbuddy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.danielsapps.packbuddycontroller.SendJson;

import com.danielsapps.packbuddycontroller.LocalDb;
import com.danielsapps.packbuddycontroller.ProfileBean;

import java.io.ByteArrayOutputStream;


public class CreateProfile extends AppCompatActivity {
    LocalDb db;
    ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
    }

    public void createProfile(View view){
        EditText name = (EditText) findViewById(R.id.nameTextEntry);
        EditText email = (EditText) findViewById(R.id.emailTextEntry);
        EditText homeCity = (EditText) findViewById(R.id.homeCityTextEntry);
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.camera);
        ProfileBean pfb = new ProfileBean(name.getText().toString(), email.getText().toString(),
                homeCity.getText().toString(), password.getText().toString(), getImageAsByte(bm));
        imv = (ImageView) findViewById(R.id.profileImage);
        SendJson sendJson = new SendJson(pfb);
        sendJson.execute();

    }

    public void setPictureAndMoveToHomePage(ProfileBean pfb){
        //This should set the picture, maybe pause for like 500 ms or 750, and then move to the home screen.


    }

    public byte[] getImageAsByte(Bitmap img){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,os);
        return os.toByteArray();
    }

    public Bitmap getImageAsBitmap(byte[] byteImg){
        Bitmap bm = BitmapFactory.decodeByteArray(byteImg, 0 , byteImg.length );
        return bm;
    }
}
