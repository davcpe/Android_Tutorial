package com.example.asus.tut1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by davcpe on 3/1/2015.
 */
public class OrderTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public OrderTABLE(Context context) { // Constructor
   objMyOpenHelper = new MyOpenHelper(context);
   writeSQLite = objMyOpenHelper.getWritableDatabase();
   readSQLite  = objMyOpenHelper.getReadableDatabase();
    }
}
