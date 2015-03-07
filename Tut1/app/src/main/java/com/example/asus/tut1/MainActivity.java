package com.example.asus.tut1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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


public class MainActivity extends Activity {

    private  UserTABLE  objUserTable;
    private  OrderTABLE objOrderTable;
    private  CoffeeTABLE objCofeeTable;
    private  String strUserChoose,strPasswordChoose,strPasswordTrue, strName;
    //public static final String url = "http://www.puneethbedre.com/rest/php_get_data.php";
    private EditText edtUser, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Bind Widget
        bindWidget();

        objUserTable = new UserTABLE(this);
        objOrderTable = new OrderTABLE(this);
        objCofeeTable = new CoffeeTABLE(this);//this == constructor, Create Table


        //testAddValue();


        //delete All data
        deleteAllData();

        //synJsonTOSQLite
        synJsonTOSQLite();

    }//OnCreate

    public void clickLogin(View view){
        strUserChoose = edtUser.getText().toString().trim();
        strPasswordChoose = edtPassword.getText().toString().trim();

        if(strUserChoose.equals("")|| strPasswordChoose.equals("")){
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDialog(MainActivity.this,"Missing Input Data","Please complete blank");
            //Alert Error
        }else{

          checkUser();

        }

    }

    private void checkUser() {
        try{
            String strData[] = objUserTable.searchUser(strUserChoose);//From EditText UserName Input
            strPasswordTrue = strData[2];
            strName  = strData[3];
            Log.d("CoffeShop","Welcome"+strName);

            checkPassword();


        }catch (Exception e){
            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDialog(MainActivity.this, "There is no user","NO"+strUserChoose +"in my Database");

        }

    }

    private void checkPassword() {
        if (strPasswordChoose.equals(strPasswordTrue)) {



            //Intent to Order Activity
            wellCOmeUser();
        }else{

            MyAlertDialog objMyAlert = new MyAlertDialog();
            objMyAlert.errorDialog(MainActivity.this,"Password False","Please Try again");
        }

    }//CheckPassword

    private void wellCOmeUser() {

        final AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.welcome_icon);
        objAlert.setTitle("Welcome to Davit Cafe");
        objAlert.setMessage("Welcome"+strName+"\n"+"to Davit's cafe");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent objIntent = new Intent(MainActivity.this, OrderActivity.class);
                objIntent.putExtra("Name",strName);
                startActivity(objIntent);
                finish();

            }
        });
        objAlert.show();


    }//welcome user

    private void bindWidget() {
        edtUser  = (EditText)findViewById(R.id.editText);
        edtPassword = (EditText)findViewById(R.id.editText2);
    }//Bind Widget

    private void deleteAllData() {

        SQLiteDatabase objSQLite = openOrCreateDatabase("Restaurant.db",MODE_PRIVATE,null);
        objSQLite.delete("userTABLE",null,null);
        objSQLite.delete("coffeeTable",null,null);
    }//delete All data


    private void synJsonTOSQLite() {
        //setUp Policy
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy mypolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(mypolicy);
        }//if
        InputStream objInputStream = null;
        String strJSON ="";//GetData
        //Create objInputStream

        try{

            HttpClient     objHttpClient   =  new DefaultHttpClient();
            HttpPost       objHttpPost     =  new HttpPost("http://www.puneethbedre.com/rest/php_get_data.php");
            HttpResponse   objHttpResponse =  objHttpClient.execute(objHttpPost);
            HttpEntity     objHttpEntity   =  objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        }catch (Exception e){
            Log.d("CoffeShop","Error from InputStram==>"+e.toString());
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
           Log.d("CoffeShop","Error Update Value==>"+e.toString());
        }

    } //synJsonTOSQLite



    private void testAddValue() {
        objUserTable.addValueUser("user","Password","Officer");
        objOrderTable.addValueOrder("Officer","Date","CoffeeOrder",4);
        objCofeeTable.addValueCoffee("Late","3.5");



    }// testAddValue


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
