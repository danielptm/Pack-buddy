package com.danielsapps.packbuddycontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by daniel on 3/28/16.
 */
public class DataConversion {

    public static String convertTo64BitString(Bitmap b){
        return Base64.encodeToString(getImageAsByte(b), Base64.DEFAULT);
    }

    public static byte[] getImageAsByte(Bitmap img){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG,0,os);
        return os.toByteArray();
    }

    public static Bitmap getImageAsBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    public static Bitmap getImageAsBitmap(String str64Bit){
        byte[] b = Base64.decode(str64Bit, Base64.DEFAULT);
        return getImageAsBitmap(b);

    }
}
