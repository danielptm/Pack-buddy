package com.danielsapps.packbuddy;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.danielsapps.packbuddycontroller.SendJson;

import org.junit.Test;
import org.junit.runner.RunWith;



/**
 * Created by daniel on 3/30/16.
 */

public class SendJsonTest extends InstrumentationTestCase {
    Context context;
    String infoTag = "info";
    String name = "Daniel";
    String email = "Danielemail";
    String homeCity = "DanielhomeCity";
    String password = "DanielPassword";


    @Test
    public void testSendJson() {
        Log.d(infoTag, "hi");
        Resources r = new  CreateProfile().getResources();
        Bitmap bm = BitmapFactory.decodeResource(r, R.drawable.daniel);
        assertNotNull(bm);




    }
}
