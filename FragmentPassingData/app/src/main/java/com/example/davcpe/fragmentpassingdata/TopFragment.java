package com.example.davcpe.fragmentpassingdata;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by davcpe on 3/31/2015.
 */
public class TopFragment extends Fragment {

    TextView tv_top;
    Button btn_top;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top,container,false);

        tv_top = (TextView)rootView.findViewById(R.id.tv_top);
        btn_top = (Button)rootView.findViewById(R.id.btn_top);
        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_top.setText("Sleep Fuck");

                Activity activity = getActivity();
                MainActivity mainActivity = (MainActivity)activity;
                mainActivity.setActivityText("ควยไรสัส");

            }
        });




        return rootView;
    }

    public void setMyText() {
        tv_top.setText("Call from Activity");
    }

}
