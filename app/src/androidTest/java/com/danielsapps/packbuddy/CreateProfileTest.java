package com.danielsapps.packbuddy;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Ignore;
import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class CreateProfileTest extends ApplicationTestCase<Application> {
    public CreateProfileTest() {
        super(Application.class);
    }

    @Ignore
    public void testRandomMethod(){
        System.out.println("hi");
    }
}