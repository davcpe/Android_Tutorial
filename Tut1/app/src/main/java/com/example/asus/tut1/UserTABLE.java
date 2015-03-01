package com.example.asus.tut1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by davcpe on 3/1/2015.
 */
public class UserTABLE {
     private MyOpenHelper objMyOpenhelper;
     private SQLiteDatabase  writeSQLite, readSQLite;

    public UserTABLE(Context context) { // Constructor
     objMyOpenhelper = new MyOpenHelper(context);
     writeSQLite = objMyOpenhelper.getWritableDatabase();
     readSQLite = objMyOpenhelper.getReadableDatabase();
    }
}
