package com.danielsapps.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by daniel on 3/25/16.
 */
public class DbHandler extends SQLiteOpenHelper {
    private final static int db_version=1;
    private final static String db_name = "pack_buddy.db";
    public final static String table_profile = "picture";
    public static final String column_name="name";
    public final static String column_id="_id";
    public static final String column_profile_picture = "profile_picture";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String initialInsert="insert into "+table_profile+" ("+column_id+", "+column_profile_picture+") values(1, 1);";

        String query ="CREATE TABLE "+table_profile+"("+
                column_id+" INTEGER, "+
                column_profile_picture+" BLOB"+");";

        db.execSQL(query);
        db.execSQL(initialInsert);
        //onUpgrade(db, 0, 0);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_profile);
        onCreate(db);

    }

    //UPDATE profile set name = 'dan' WHERE id=1;
    public void insertPicture(byte[] b){
        //String insertStatement="UPDATE "+table_profile+" SET "+column_profile_picture+" = "+b+" WHERE "+column_id+"= 1;"
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_id, 1);
        cv.put(column_profile_picture, b);
        db.update(table_profile, cv, column_id+"=1", null );
        db.close();
    }

    //Something wrong with the below.Sql statement is wrong.
    public byte[] selectPicture(){
        SQLiteDatabase db = getReadableDatabase();
        String selectStatement = "SELECT "+ column_profile_picture+" FROM "+table_profile+" WHERE "+column_id+" = 1;";
        Cursor c = db.rawQuery(selectStatement, null);
        c.moveToFirst();
        byte[] b = c.getBlob(0);
        c.close();
        db.close();
        System.out.println("SUCCESS, SELECTED Byte[] "+b.toString());
        return b;

    }
}
