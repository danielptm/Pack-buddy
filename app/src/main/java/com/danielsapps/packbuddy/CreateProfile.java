package com.danielsapps.packbuddy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.danielsapps.packbuddycontroller.SendJson;


public class CreateProfile extends AppCompatActivity {
    ImageView imv;
    String img;
    byte[] byt;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        System.out.println("In createProfile()!");
//        Bundle b = getIntent().getExtras();
//        if(b==null){
//            return;
//        }
//        img = b.getString("image");
//        byt = Base64.decode(img, Base64.DEFAULT);
//        bm = BitmapFactory.decodeByteArray(byt, 0, byt.length);
//        imv.setImageBitmap(bm);
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
        SendJson sendJson = new SendJson(name.getText().toString(), email.getText().toString(),
                homeCity.getText().toString(), password.getText().toString(),bm);
        sendJson.execute();

    }


}
