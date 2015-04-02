package app.z0nen.slidemenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Z0NEN on 10/22/2014.
 */
public class menu1_Fragment extends Fragment {

    private Spinner spinner_Act,spinner_Scale;
    private ImageView imgStart,img_Act,img_Scale;
    private MyAlertDialog objAlertDialog;
    ArrayAdapter<CharSequence> adapterAct,adapterScale;
    private  String strItemAct,strScale;
    private Context context;



    //SetUpTimer

    TextView mButtonLabel;

    // Counter of time since app started ,a background task
    private long mStartTime = 0L;
    private TextView mTimeLabel,mTimerLabel;

    // Handler to handle the message to the timer task
    private Handler mHandler = new Handler();

    static final int UPDATE_INTERVAL = 1000;



    String timerStop1;


    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);

        //BindWidget
        spinner_Act   = (Spinner)rootview.findViewById(R.id.spinnerACT);
        spinner_Scale = (Spinner)rootview.findViewById(R.id.spinnerScale);
        imgStart      = (ImageView)rootview.findViewById(R.id.imageStartbtn);
        img_Act       = (ImageView)rootview.findViewById(R.id.imageACT);
        img_Scale     = (ImageView)rootview.findViewById(R.id.imageScale);
        //Timer
        mTimerLabel = (TextView) rootview.findViewById(R.id.textTimer);


        //SetUpListView
        SetUpListView();

        //SetUpStartEvent
        SetUpStartEvent();





        return rootview;
    }

    private void SetUpListView() {
        //Spinner Act
        adapterAct = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.activity,android.R.layout.simple_spinner_item);
        adapterAct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Act.setAdapter(adapterAct);
        spinner_Act.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position = spinner_Act.getSelectedItemPosition();

                switch (position) {
                    case 0:
                        strItemAct="Cycling";
                        break;
                    case 1:
                        strItemAct="Sleep";
                        break;
                }

                if(strItemAct == "Sleep"){
                    img_Act.setImageResource(R.drawable.sleep_icon);
                }
                else if(strItemAct =="Cycling"){
                    img_Act.setImageResource(R.drawable.cycling_icon);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Spinner Scale
        adapterScale = ArrayAdapter.createFromResource(getActivity().getBaseContext(),R.array.scale,android.R.layout.simple_spinner_item);
        adapterScale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Scale.setAdapter(adapterScale);
        spinner_Scale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        strScale="Kcal";
                        break;
                    case 1:
                        strScale="Distance";
                        break;
                }

                if(strScale == "Kcal"){
                    img_Scale.setImageResource(R.drawable.kcal_icon);
                }
                else if(strScale =="Distance"){
                    img_Scale.setImageResource(R.drawable.distance_icon);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }//SetUpListView






    private void SetUpStartEvent() {

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgStart.setImageResource(R.drawable.stop_icon2);
                if(mStartTime == 0L){
                    mStartTime = SystemClock.uptimeMillis();
                    mHandler.removeCallbacks(mUpdateTimeTask);
                    mHandler.postDelayed(mUpdateTimeTask, 100);

                    menu2_Fragment objMenu2_fragment = new menu2_Fragment();
                    Bundle bundle =new Bundle();
                    bundle.putString("Activity_Select", strItemAct);
                    objMenu2_fragment.setArguments(bundle);




                }
                imgStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHandler.removeCallbacks(mUpdateTimeTask);
                        mTimerLabel.setText(timerStop1);
                        mStartTime = 0L;














                    }
                });

            }
        });
    }//SetUpStartEvent

    private void InviteFriendDialog() {


    }

    private Runnable mUpdateTimeTask = new Runnable(){

        public void run() {

            final long start = mStartTime;
            long millis = SystemClock.uptimeMillis()- start;

            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            mTimerLabel.setText("" + minutes + ":"
                    + String.format("%02d", seconds));

            timerStop1 = minutes + ":"
                    + String.format("%02d", seconds);

            mHandler.postDelayed(this, 200);

        }
    };



}
