package com.example.davcpe.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.security.SecurityPermission;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private Button btnGO;
    private EditText edtName,edtEmail;
    private Spinner MySpinner;
    private String strName,strEmail,strMySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialWidget();
        
        ChooseSpinner();



        btnGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strName = edtName.getText().toString();
                strEmail = edtEmail.getText().toString();
                strMySpinner = String.valueOf(MySpinner.getSelectedItem());

                NextPage();

            }
        });
    }

    private void NextPage() {

        Intent intent = new Intent(MainActivity.this,MainActivity2.class);

        intent.putExtra("strName",strName);
        intent.putExtra("strEmail",strEmail);
        intent.putExtra("Myspinner",strMySpinner);

        startActivity(intent);

    }

    private void ChooseSpinner() {
        ArrayList<String> myListSpinner = new ArrayList<String>();
        myListSpinner.add("Item 1");
        myListSpinner.add("Item 2");
        myListSpinner.add("Item 3");
        myListSpinner.add("Item 4");
        myListSpinner.add("Item 5");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myListSpinner );
        MySpinner.setAdapter(myAdapter);
    }

    private void initialWidget() {

        edtName = (EditText)findViewById(R.id.editName);
        edtEmail = (EditText)findViewById(R.id.editEmail);
        MySpinner = (Spinner)findViewById(R.id.spinner);
        btnGO = (Button)findViewById(R.id.btnGo);
    }


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
