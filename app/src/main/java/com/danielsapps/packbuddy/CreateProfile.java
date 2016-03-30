package com.danielsapps.packbuddy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.danielsapps.packbuddycontroller.SendJson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class CreateProfile extends AppCompatActivity {
    String info="info";
    ImageView iv;
    Bitmap bm;
    InputStream is;
    byte[] imageByte;
    Bitmap decodeBm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        String b = (String) getIntent().getExtras().get("image");
        String filePath = getFilesDir() + "/" + b;

        Log.d(info, "line 37");
        Log.d(info, filePath);
        File f = new File(filePath);
        imageByte = new byte[(int) f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(imageByte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.delete();
        Log.d(info, "line 48");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        iv = (ImageView) findViewById(R.id.imageView);
        bm = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//        bm.compress(Bitmap.CompressFormat.PNG, 25, baos);
//        try {
//            baos.flush();
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        decodeBm = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));
//        try {
//            baos.flush();
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        iv.setImageBitmap(bm);
        iv.setRotation(270);
//    }
    }

    /**
     * Gets the values entered by the user. Prepares them by creating a SendJson object.
     * Starts the thread, to send them. Receives a Bitmap object and sets it to the profile
     * picture in the UI.
     * @param view
     */

    public void createProfile(View view){
        EditText name = (EditText) findViewById(R.id.nameTextEntry);
        EditText email = (EditText) findViewById(R.id.emailTextEntry);
        EditText homeCity = (EditText) findViewById(R.id.homeCityTextEntry);
        EditText password = (EditText) findViewById(R.id.passwordEditText);
        String stringName = name.getText().toString();
        String stringEmail = email.getText().toString();
        String stringHomeCity = homeCity.getText().toString();
        String stringPassword = password.getText().toString();
        SendJson sendJson = new SendJson(stringName, stringEmail,
                stringHomeCity, stringPassword,bm);
        sendJson.execute();
//        Intent i = new Intent(this, HomePage.class);
//        i.putExtra("email", stringEmail);
//        i.putExtra("password", stringPassword);
//        startActivity(i);

    }
}