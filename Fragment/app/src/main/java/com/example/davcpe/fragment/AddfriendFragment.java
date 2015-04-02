package com.example.davcpe.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddfriendFragment extends Fragment {




    public AddfriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addfriend, container, false);


        return rootView;
    }


}
