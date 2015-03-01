package com.example.asus.tut1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davcpe on 3/1/2015.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "Restaurant.db" ;
    private static  final int    DATABASE_VERSION = 1 ;
    private static  final String USER_TABLE ="create table userTABLE (_id integer primary key,"+" user text, Password text, Officer text);";
    private static  final String ORDER_TABLE ="create table orderTABLE(_id integer primary key, "+" officer text, Date text, CoffeeOrder text, Item integer);";


    public MyOpenHelper(Context context) { // Constructor
        super(context,DATABASE_NAME,null,DATABASE_VERSION); //Constructor

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_TABLE);
        db.execSQL(ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
