package com.danielsapps.packbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.danielsapps.fragments.ListViewAdapter;
import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddycontroller.HostelLocations;
import com.danielsapps.packbuddycontroller.LoadProfileAndGuests;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author daniel
 */
public class HomePage extends AppCompatActivity {
    ArrayList<ProfileBean> pfbs;
    ArrayList<ProfileBean> editedList;
    String password;
    String hostel;
    String email;
    ListView lv;
    HostelLocations hl;
    ProfileBean user;
    int pageToLoad=0;
    String homePageLog="Homepage";
    String user_pref="main user preferences";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        hostel = getIntent().getStringExtra("hostel");
        email = getIntent().getStringExtra("email");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(hostel);
        setSupportActionBar(toolbar);
        password = getIntent().getStringExtra("password");
        lv = (ListView) findViewById(R.id.CheckInHostelListview);
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Travelers");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Travelers");
        host.addTab(spec);

        TabHost.TabSpec spec2 = host.newTabSpec("To do");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("To do");
        host.addTab(spec2);

        TabHost.TabSpec spec3 = host.newTabSpec("Feed");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Feed");
        host.addTab(spec3);



    }

    @Override
    protected void onResume() {
        super.onResume();
        pfbs = getCheckedInFromDb();
        user = pfbs.get(0);
        loadCheckedIns();
    }

    public ArrayList<ProfileBean> getCheckedInFromDb(){
        ArrayList<ProfileBean> alpfbs=null;
        LoadProfileAndGuests lp = new LoadProfileAndGuests(email,hostel, pageToLoad);
        pageToLoad+=3; //Increments so that the next time the function is called the next 3 columns will be retrieved.
        Log.d(homePageLog, "Right before lp.execute");
        lp.execute();
        try {
            alpfbs = lp.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(homePageLog,"Right before the for loop");
        Log.d(homePageLog, String.valueOf(alpfbs.size()));
        for(ProfileBean pfb: alpfbs){
            Log.d(homePageLog, pfb.getName());
        }
        return alpfbs;
    }


    public void loadCheckedIns(){
        editedList = new ArrayList<ProfileBean>();

        for(ProfileBean pfb: pfbs){
            if(! pfb.getEmail().equals(user.getEmail())){
                editedList.add(pfb);
            }
        }
        ListAdapter lad = new ListViewAdapter(this, editedList);
        lv.setAdapter(lad);
    }

    public void goToProfile(View v){
        Intent i = new Intent(this, Profile.class);
        i.putExtra("hostel", hostel);
        startActivity(i);
    }
}
