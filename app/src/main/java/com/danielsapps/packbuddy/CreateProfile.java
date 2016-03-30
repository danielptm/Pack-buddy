package com.danielsapps.packbuddy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.danielsapps.packbuddycontroller.SendJson;


public class CreateProfile extends AppCompatActivity {
    ImageView iv;
    Bitmap bm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        iv = (ImageView) findViewById(R.id.imageView);
        bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.daniel);
        iv.setImageBitmap(bm);
    }


    @Override
    protected void onResume(){
        super.onResume();

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