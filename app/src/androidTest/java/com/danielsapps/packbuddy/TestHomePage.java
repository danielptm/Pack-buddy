package com.danielsapps.packbuddy;

import android.util.Log;

import com.danielsapps.model.ProfileBean;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Created by daniel on 4/5/16.
 */
public class TestHomePage {
    String Test="HomePage::";
    ArrayList<ProfileBean> als;

    @Test
    public void testMain(){
        System.out.println("Hi");
        System.out.println("Hi");

        HomePage hp = new HomePage();
        als =hp.loadUsers();

        for(ProfileBean pfb: als){
            Log.d(Test, pfb.getName());
            System.out.println(pfb.getName());
        }
    }
}
