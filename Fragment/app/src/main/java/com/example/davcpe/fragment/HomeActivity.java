package com.example.davcpe.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends FragmentActivity {
    MyPagerAdapter adapter;
    ViewPager pager;
    private ImageView imgCategory,imgAddfriend,imgRank,imgNotification,imgMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


        imgCategory = (ImageView)findViewById(R.id.imageCategory);

        imgCategory.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });



       imgAddfriend = (ImageView)findViewById(R.id.imageAddFriend);
        imgAddfriend.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });


        imgRank = (ImageView)findViewById(R.id.imageRanking);
        imgRank.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });

        imgNotification = (ImageView)findViewById(R.id.imageNotification);
        imgNotification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3);
            }
        });





    }
}
