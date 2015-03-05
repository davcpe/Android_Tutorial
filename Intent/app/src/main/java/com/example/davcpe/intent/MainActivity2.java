package com.example.davcpe.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity2 extends Activity {

    private Button btnBack;
    private TextView txtName,txtEmail,txtSpinner;
    private String strReachName, strReachEmail, strReachMySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        initialData();

        reachValues();

        SetUpText();






        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                finish();
            }
        });
    }





    private void initialData() {
        txtName = (TextView)findViewById(R.id.ansName);
        txtEmail = (TextView)findViewById(R.id.ansEmail);
        txtSpinner = (TextView)findViewById(R.id.ansChoose);

        btnBack = (Button)findViewById(R.id.btnBack);
    }

    private void reachValues() {

        strReachName = getIntent().getStringExtra("strName");
        strReachEmail = getIntent().getStringExtra("strEmail");
        strReachMySpinner = getIntent().getStringExtra("Myspinner");
    }

    private void SetUpText() {
        txtName.setText(strReachName);
        txtEmail.setText(strReachEmail);
        txtSpinner.setText(strReachMySpinner);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
