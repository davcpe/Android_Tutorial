package com.example.asus.tut1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ASUS on 3/4/2015.
 */
public class CoffeeTABLE {
    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static  final String Coffee_TABLE = "coffeeTable";
    public static  final String COLUMN_ID_COFEE  ="_id";
    public static  final String COLUMN_COFEE = "Coffee";
    public static  final String COLUMN_PRICE = "Price";



    public CoffeeTABLE(Context context){

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    //Add Value to Coffee Table
    public long addValueCoffee(String strCoffee, String strPrice){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_COFEE,strCoffee);
        objContentValues.put(COLUMN_PRICE, strPrice);

        return writeSQLite.insert(Coffee_TABLE,null,objContentValues);
    }

}//MainClass
