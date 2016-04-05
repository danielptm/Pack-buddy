package com.danielsapps.packbuddy;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.danielsapps.packbuddycontroller.SendJson;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class CreateProfileTest extends ActivityInstrumentationTestCase2 {
    public CreateProfileTest() {
        super(Application.class);
    }

    @Ignore
    public void testSendJson() {
        String infoTag = "info";

        //Log.i(infoTag,"*********hi");
        System.out.println("*********************Hi from testing");

/*        String name = "daniel";
        String email = "danielsEmail";
        String homecity = "danielsHomeCity";
        String password = "danielsPassword";
        Bitmap bm = BitmapFactory.decodeResource(getInstrumentation().getContext().getResources(), R.drawable.daniel);
        Log.i(infoTag, "******");

        SendJson sendJson = new SendJson(name, email, homecity, password, bm);

        sendJson.execute();*/


    }

}

