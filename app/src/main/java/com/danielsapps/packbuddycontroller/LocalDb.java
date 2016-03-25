package com.danielsapps.packbuddycontroller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.danielsapps.model.ProfileBean;

/**
 * Created by daniel on 3/20/16.
 */
public class LocalDb extends SQLiteOpenHelper{
    private final static int db_version=1;
    private final static String db_name = "pack_buddy.db";
    public final static String table_profile = "profiles";
    public final static String column_id="_id";
    public static final String column_name="name";
    public static final String column_email="email";
    public static final String column_home_city="home_city";
    public static final String column_password = "password";
    public static final String column_profile_picture = "profile_picture";

    public LocalDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+table_profile+"("+
                column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                column_name+" text, "+column_email+" text, "+column_home_city+" text, "+
                column_password+" text, "+column_profile_picture+" BLOB"+");";
        db.execSQL(query);
        onUpgrade(db,0,0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ table_profile);
        onCreate(db);

    }
    //Creates a new row of data in db
    public void createRow(ProfileBean pfb){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(column_name, pfb.getName());
//        values.put(column_email, pfb.getEmail());
//        values.put(column_home_city, pfb.getHomeCity());
//        values.put(column_password, pfb.getPassword());
//        values.put(column_profile_picture, pfb.getImageAsByte());
//        db.insert(table_profile, null, values);
//        db.close();
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+db_name);
    }

    public Bitmap getProfilePictureFromDbAsBitmap(){
        String query="SELECT * FROM profiles WHERE _id=3;" ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        byte[] b = c.getBlob(5);
        Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bm;
    }

    //Deletes a row of data from the db
    public void deleteRow(){

    }
}

//TODO: Do these now first
//Impelement the above methods.
//Write methods that convert an image to byte[] and convert back to png, and display the image in ImageView