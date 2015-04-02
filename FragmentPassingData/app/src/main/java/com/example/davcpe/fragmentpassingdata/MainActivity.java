package com.example.davcpe.fragmentpassingdata;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView tv_activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_activity = (TextView)findViewById(R.id.tv_activity);

        Button btn_main = (Button)findViewById(R.id.buttonMainAct);
        btn_main.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_top);
                TopFragment fragmentTop = (TopFragment)fragment;
                fragmentTop.setMyText();
            }
        });
    }

    public void setActivityText(String str) {
        tv_activity.setText(str);
    }
}
