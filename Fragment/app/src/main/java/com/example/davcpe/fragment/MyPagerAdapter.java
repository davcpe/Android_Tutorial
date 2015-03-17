package com.example.davcpe.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by davcpe on 3/17/2015.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {



    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position ==0)
            return  new CategoryFragment();
        else if(position == 1)
            return  new AddfriendFragment();
        else if(position == 2)
            return  new RankFragment();
        else if(position == 3)
            return  new NotificationFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
