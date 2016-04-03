package com.danielsapps.packbuddycontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.danielsapps.model.EmailAndPasswordBean;
import com.danielsapps.model.ProfileBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by daniel on 3/28/16.
 */
public class DataConversion {

//    public static String convertTo64BitString(Bitmap b){
//        return Base64.encodeToString(getImageAsByte(b), Base64.DEFAULT);
//    }
//
//    public static byte[] getImageAsByte(Bitmap img){
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        img.compress(Bitmap.CompressFormat.PNG,0,os);
//        return os.toByteArray();
//    }
//
//    public static Bitmap getImageAsBitmap(byte[] b){
//        return BitmapFactory.decodeByteArray(b, 0, b.length);
//    }
//
//    public static Bitmap getImageAsBitmap(String str64Bit){
//        byte[] b = Base64.decode(str64Bit, Base64.DEFAULT);
//        return getImageAsBitmap(b);

//    }

    public static String convertStreamToString(InputStream is){
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
//                Log.i(info, line);
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }

    public static String convertStreamToString2(InputStream is){
        BufferedReader r;
        StringBuilder total = new StringBuilder();
        r = new BufferedReader(new InputStreamReader(is));

        String line;
        try {
            while (( line = r.readLine() ) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return total.toString();
    }

    public static String getJsonStringWithGson(ProfileBean pfb){
        Gson gson=null;
        gson = new Gson();
        String x = gson.toJson(pfb, ProfileBean.class);
        return x;
    }

    public static String getJsonStringWithGson(EmailAndPasswordBean epb){
        Gson gson=null;
        gson = new Gson();
        String x = gson.toJson(epb, EmailAndPasswordBean.class);
        return x;
    }


//
//    public static JSONObject convertToJsonObject(String str){
//        JSONObject jobj = new JSONObject();
//        try {
//            jobj.getJSONObject(str);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jobj;
//    }

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
