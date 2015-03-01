package com.example.asus.tut1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by davcpe on 3/1/2015.
 */
public class UserTABLE {
     private MyOpenHelper objMyOpenhelper;
     private SQLiteDatabase  writeSQLite, readSQLite;
     public static final String TABLE_USER = "userTABLE";
     public static final String COLUMN_ID_USER  ="_id";
     public static final String COLUMN_USER = "user";
     public static final String COLUMN_PASSWORD = "Password";
     public static  final String COLUMN_OFFICER = "Officer";

    public UserTABLE(Context context) {
     objMyOpenhelper = new MyOpenHelper(context);
     writeSQLite = objMyOpenhelper.getWritableDatabase();
     readSQLite = objMyOpenhelper.getReadableDatabase();
    }// Constructor

    public long addValueUser (String strUser, String strPassword, String strOfficer){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        return writeSQLite.insert(TABLE_USER, null,objContentValues);
    } //addValueToUser
}
