package com.danielsapps.packbuddy;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.danielsapps.packbuddycontroller.SendJson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;


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
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        iv = (ImageView) findViewById(R.id.imageView);
        bm = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length, options);
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

    public void createProfile(View view) throws ExecutionException, InterruptedException {
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
        String message = sendJson.get();
        if(message.equals("Welcome!")) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT);
            Intent i = new Intent(this, HostelSearch.class);
            i.putExtra("email", stringEmail);
            i.putExtra("name", stringName);
            startActivity(i);
        }
        else {
            Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            t.show();

        }
    }
}