package com.danielsapps;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddy.R;
import com.danielsapps.packbuddycontroller.DataConversion;
import com.danielsapps.packbuddycontroller.GetProfileFromDb;

import java.util.concurrent.ExecutionException;

public class HomePage extends AppCompatActivity {
    ProfileBean pfb;
    ImageView iv;
    TextView tv;
    GetProfileFromDb gpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Hostel name");
        Bundle b = getIntent().getExtras();
        String email = b.getString("email");
        String password = b.getString("password");
        gpd = new GetProfileFromDb(email, password);
        gpd.execute();


    }

    @Override
    protected void onResume(){
        try {
            pfb = gpd.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.onResume();
        iv = (ImageView) findViewById(R.id.hpImage);
        tv = (TextView) findViewById(R.id.hpName);
//        System.out.println("******************************** image: " +pfb.getImg());
        tv.setText(pfb.getName());
        iv.setImageBitmap(DataConversion.getImageAsBitmap(pfb.getImg()));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu mi){
        getMenuInflater().inflate(R.menu.menu_start_screen, mi);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(mi);
    }

}
