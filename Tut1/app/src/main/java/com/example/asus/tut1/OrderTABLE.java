package com.example.asus.tut1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by davcpe on 3/1/2015.
 */
public class OrderTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static  final String TABLE_ORDER = "orderTABLE";
    public static  final String COLUMN_ID_ORDER  ="_id";
    public static  final String COLUMN_OFFICER = "Officer";
    public static  final String COLUMN_DATE = "Date";
    public static  final String COLUMN_COFFEE = "CoffeeOrder";
    public static  final String COLUMN_ITEM = "Item";



   public OrderTABLE(Context context) {
   objMyOpenHelper = new MyOpenHelper(context);
   writeSQLite = objMyOpenHelper.getWritableDatabase();
   readSQLite  = objMyOpenHelper.getReadableDatabase();
    }// Constructor

    public long addValueOrder(String strOfficer, String strDate , String strCoffee, int intItem ){

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_DATE, strDate);
        objContentValues.put(COLUMN_COFFEE, strCoffee);
        objContentValues.put(COLUMN_ITEM, intItem);
        return writeSQLite.insert(TABLE_ORDER, null,objContentValues);

    }
}
