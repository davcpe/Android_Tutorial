package com.example.asus.tut1;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ASUS on 3/3/2015.
 */
public class JSONSynData {

    private  UserTABLE  objUserTable;
    private  OrderTABLE objOrderTable;

    public  JSONSynData(){

    }

    private void synJsonTOSQLite(Context context) {

        objUserTable = new UserTABLE(context);
        objOrderTable = new OrderTABLE(context);

        //setUp Policy
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy mypolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        }//if
        InputStream objInputStream = null;
        String strJSON ="";

        //Create objInputStream
        try{

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost   =  new HttpPost("http://www.puneethbedre.com/rest/php_get_data.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        }catch (Exception e){
            Log.d("CoffeShop", "Error from InputStram==>" + e.toString());
        }

        //Change InputStream to String
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream ,"UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while((strLine = objBufferedReader.readLine()) != null){
                objStringBuilder.append(strLine);
            }//while
            objInputStream.close();
            strJSON = objStringBuilder.toString();

        }catch (Exception e){
            Log.d("CoffeShop","Error Create String==>"+e.toString());
        }

        //Up Value to SQLite
        try{
            final JSONArray objJSONArray =  new JSONArray(strJSON);
            for(int i=0;i< objJSONArray.length();i++){
                JSONObject objJSONObject = objJSONArray.getJSONObject(i);
                String strUser = objJSONObject.getString("user");
                String strPassword = objJSONObject.getString("Password");
                String strOfficer = objJSONObject.getString("Officer");

                long insertValue  = objUserTable.addValueUser(strUser,strPassword,strOfficer);

            }//for
        }catch (Exception e ){
            Log.d("CoffeShop","Error Up Value==>"+e.toString());
        }

    } //synJsonTOSQLite



}
