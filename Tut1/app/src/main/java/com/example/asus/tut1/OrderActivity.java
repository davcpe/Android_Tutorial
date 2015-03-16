package com.example.asus.tut1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class OrderActivity extends Activity {

    //Explicit
    private  CoffeeTABLE objCoffeeTable;
    private  String[] strListCoffee, strListPrice;
    private  int[]myTarget;
    private  TextView txtShowOfficer;
    private  EditText edtDesk;
    private  String strMyOfficer, strMyDesk, strMyCoffee,strItems;


   // private  CoffeeTABLE objCofeeTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Bind Widget
        bindWidget();

        objCoffeeTable = new CoffeeTABLE(this);
        //strMyDesk  =  edtDesk.getText().toString().trim();

        //SetUp Text Officer
        setUpTxtShow();

        //Synchronize JSON SQLite
        synchronizeJSONtoCoffee();

        //setUp All Array
        setUPAllArray();

        //Create ListView
        createListView();




    }//Oncreate

    private void setUpTxtShow() {

        strMyOfficer = getIntent().getExtras().getString("Name");
        txtShowOfficer.setText(strMyOfficer);
    }//SetUp Text Officer

    private void bindWidget() {

        txtShowOfficer = (TextView)findViewById(R.id.txtShowOfficer);
        edtDesk        = (EditText)findViewById(R.id.edtDesk);
    }//bindWidget

    private void createListView() {

        int[]myTarget = {R.drawable.coffee_latte_1,R.drawable.coffee_latte_2,R.drawable.coffee_latte_3,R.drawable.coffee_latte_4,R.drawable.coffee_latte_5}; // Add Images

        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strListCoffee,strListPrice,myTarget);
        final ListView objListView = (ListView)findViewById(R.id.CoffeelistView);
        objListView.setAdapter(objMyAdapter);  // Fusion Part with Activity_order and List_view_row

        //Active Click
        objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Check Zero
                strMyDesk = edtDesk.getText().toString().trim();
                if(strMyDesk.equals("")){
                    MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                    objMyAlertDialog.errorDialog(OrderActivity.this,"Customer's table?","Please input table number");

                }else{
                        strMyCoffee = strListCoffee[position];
                        //maschecklog();

                    //Show Choose Items

                    ShowChooseItem();
                }

            }//event
        });

    }//createListView

    private void ShowChooseItem() {

        CharSequence[] charItems = {"1 cup ","2 cups","3 cups","4 cups","5 cups"};

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.questionicon);
        objBuilder.setTitle("How many cups?");
        objBuilder.setCancelable(false);
        objBuilder.setSingleChoiceItems(charItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case 0:
                         strItems="1";
                         break;
                    case 1:
                        strItems ="2";
                        break;
                    case 2:
                        strItems="3";
                        break;
                    case 3:
                        strItems="4";
                        break;
                    case 4:
                        strItems="5";
                        break;

                }//switch

                dialog.dismiss();

                checklog();

                //Up New Order
                upNewOrder();

            }
        });
        AlertDialog objAlertDialog = objBuilder.create();
        objAlertDialog.show();

    }//ShowChooseItem

    private void upNewOrder() {
        //SetUp Policy
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);


        }

        //UpValue
        try{

            ArrayList<NameValuePair>objNameValuePair = new ArrayList<NameValuePair>();
            objNameValuePair.add(new BasicNameValuePair("isADD","true"));
            objNameValuePair.add(new BasicNameValuePair("Officer",strMyOfficer));
            objNameValuePair.add(new BasicNameValuePair("Desk",strMyDesk));
            objNameValuePair.add(new BasicNameValuePair("CoffeeOrder",strMyCoffee));
            objNameValuePair.add(new BasicNameValuePair("Amount",strItems));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost  = new HttpPost("http://puneethbedre.com/rest/add_order.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePair,"UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(OrderActivity.this,"Update"+strMyOfficer,Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.d("CoffeShop","Update mySQL==>"+e.toString());
        }
    }

    private void checklog() {
        Log.d("order","Officer==>"+strMyOfficer);
        Log.d("order","Desk No.==>"+strMyDesk);
        Log.d("order","Coffee ==>"+strMyCoffee);
        Log.d("order","Item ==>"+strItems);
    }

    private void setUPAllArray() {
        strListCoffee = objCoffeeTable.listCoffee();
        strListPrice = objCoffeeTable.listPrice();

        //int[]myTarget = {R.drawable.coffee_latte_1,R.drawable.coffee_latte_2,R.drawable.coffee_latte_3,R.drawable.coffee_latte_4,R.drawable.coffee_latte_5}; // Add Images

    }//setUpAllArray

    private void synchronizeJSONtoCoffee() {
        //Change Policy
        if(Build.VERSION.SDK_INT>9){

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }//if

        InputStream objInputStream = null;
        String strJSON ="";

        //Create InputStream
        try {

            HttpClient       objHttpClient   =  new DefaultHttpClient();
            HttpPost         objHttpPost     =  new HttpPost("http://www.puneethbedre.com/rest/php_get_data_Coffee.php");
            HttpResponse     objHttpResponse =  objHttpClient.execute(objHttpPost);
            HttpEntity       objHttpEntity   =  objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        }catch (Exception e){
            Log.d("CoffeShop","Error from InputStram==>"+e.toString());
        }

        //Create str JSON

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

        // Update Value to SQLite

        try {
            final JSONArray objJSONArray =  new JSONArray(strJSON);
            for(int i=0;i< objJSONArray.length();i++){
                JSONObject objJSONObject = objJSONArray.getJSONObject(i);
                String strCoffee = objJSONObject.getString("Coffee");
                String strPrice = objJSONObject.getString("Price");
               // CoffeeTABLE objCofeeTable = new CoffeeTABLE(this);

                long insertValueCoffee  = objCoffeeTable.addValueCoffee(strCoffee,strPrice);

            }//for


        }catch (Exception e){
            Log.d("CoffeShop","Error Update Value==>"+e.toString());
        }
    }//SynJSON Coffee


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
}//OrderClass
