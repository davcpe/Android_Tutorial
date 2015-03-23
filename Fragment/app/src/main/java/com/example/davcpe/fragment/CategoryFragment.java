package com.example.davcpe.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.davcpe.fragment.R.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment  {

    private ImageView imgStart;
    private EditText edtActivity;
    private Spinner spinnerActivity;
    private static final String[] activities = {"walk","cycling","yoga"};




    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View rootView = inflater.inflate(layout.fragment_category, container, false);

        //Images
        imgStart = (ImageView)rootView.findViewById(id.imageButtonStart);

        //EditTexts
        edtActivity = (EditText)rootView.findViewById(id.editAct);

        //SpinnerAct
        spinnerActivity = (Spinner)rootView.findViewById(id.spinnerAct);



        //SelectActivity
        SelectActivity();


        //TestSpinner
        TestSpinner();


        //Click a Start Button
        clickStartButton();





        return rootView;
    }

    private void TestSpinner() {


    }//TestSpinner

    private void SelectActivity() {

        edtActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSelectItem();
            }
        });

    }//SelectActivity

    private void showSelectItem() {



    }//showSelectItem

    private void clickStartButton() {

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgStart.setImageResource(drawable.stop_icon2);

            }
        });
    }//clickStartButton

}
