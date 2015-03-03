package com.example.asus.tut1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public String[]searchUser(String strUSer){
        try {

            String strData[]= null;
            Cursor objCursor = readSQLite.query(TABLE_USER,
                    new String[] {COLUMN_ID_USER, COLUMN_USER,COLUMN_PASSWORD,COLUMN_OFFICER},COLUMN_USER+"=?",
                    new String[] {String.valueOf(strUSer)}, null,null,null,null);
            if (objCursor != null) {
                if(objCursor.moveToFirst()){
                    strData = new String[objCursor.getColumnCount()];
                    strData[0] = objCursor.getString(0);
                    strData[1] = objCursor.getString(1);
                    strData[2] = objCursor.getString(2);
                    strData[3] = objCursor.getString(3);
                }// if2
            }//if1

            objCursor.close();
            return strData;
        }catch (Exception e){
            return null;
        }


    }//search User

    public long addValueUser (String strUser, String strPassword, String strOfficer){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        return writeSQLite.insert(TABLE_USER, null,objContentValues);
    } //addValueToUser
}
