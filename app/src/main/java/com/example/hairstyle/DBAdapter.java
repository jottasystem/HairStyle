package com.example.hairstyle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "mydatabase.sqllite";
    public static final String TAG = "MyTAG";
    public static final int SCHEMA_VERSION =1;

    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE IF NOT EXISTS user_table ( IDUSER INTEGER primary key  autoincrement, "+
               "USERNAME VARCHAR, "+
               "PASSWORD VARCHAR ) ; ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public DBAdapter(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_VERSION);
    }

    public Cursor callData(String login, String password){
        return (getReadableDatabase().rawQuery("SELECT * FROM user_table ",null));
    }

    public long inserUser(String username, String Password){
        ContentValues initialValues = new ContentValues();
        initialValues.put("USERNAME",username);
        initialValues.put("PASSWORD",Password);
        return getWritableDatabase().insert("user_table",null,initialValues);
    }
}
