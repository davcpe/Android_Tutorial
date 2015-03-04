package com.example.asus.tut1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    public String[]listPrice(){

        String strlistPrice[] = null;
        Cursor objCursor = readSQLite.query(Coffee_TABLE,
                new String[]{COLUMN_ID_COFEE,COLUMN_PRICE},null,null,null,null,null);
        objCursor.moveToFirst();
        strlistPrice = new String[objCursor.getCount()];

        for(int i=0;i<objCursor.getCount();i++){
            strlistPrice[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return  strlistPrice;

    }//ListPrice


    public String[]listCoffee(){

       String strListCoffee[] = null;
        Cursor objCursor = readSQLite.query(Coffee_TABLE,
                new String[]{COLUMN_ID_COFEE,COLUMN_COFEE},null,null,null,null,null);

        objCursor.moveToFirst();
        strListCoffee = new String[objCursor.getCount()];

        for(int i =0; i<objCursor.getCount();i++){
            strListCoffee[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_COFEE));
            objCursor.moveToNext();
        }//for
        objCursor.close();
        return strListCoffee;
    }//ListCoffee

    //Add Value to Coffee Table
    public long addValueCoffee(String strCoffee, String strPrice){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_COFEE,strCoffee);
        objContentValues.put(COLUMN_PRICE, strPrice);

        return writeSQLite.insert(Coffee_TABLE,null,objContentValues);
    }

}//MainClass
