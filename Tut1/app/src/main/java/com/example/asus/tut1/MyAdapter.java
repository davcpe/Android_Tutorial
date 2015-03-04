package com.example.asus.tut1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ASUS on 3/4/2015.
 */
public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context objContext ;
    private String[] strNameCoffee, strPriceCoffee;
    private int[] intMyTarget;



    public MyAdapter(Context context, String[] strname, String[] strPrice, int[] targetID){

        this.objContext = context;
        this.strNameCoffee = strname;
        this.strPriceCoffee = strPrice;
        this.intMyTarget = targetID;



    }//Constructor

    @Override
    public int getCount() {
        return  strNameCoffee.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater)objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_row, parent, false);


        //Setup Text Coffee
        TextView listViewCoffee  =  (TextView)view.findViewById(R.id.txtShowCoffee);
        listViewCoffee.setText(strNameCoffee[position]);

        //Setup Text Price
        TextView listViewPrice = (TextView)view.findViewById(R.id.txtShowPrice);
        listViewPrice.setText(strPriceCoffee[position]);

        //Setup Image
        ImageView listImageCoffee = (ImageView)view.findViewById(R.id.imgCoffee);
        listImageCoffee.setBackgroundResource(intMyTarget[position]);

        return view;
    }//getView
}//MainClass
