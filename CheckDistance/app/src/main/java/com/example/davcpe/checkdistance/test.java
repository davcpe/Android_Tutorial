package com.example.davcpe.checkdistance;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by davcpe on 4/1/2015.
 */
public class test extends Activity implements Runnable {
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 30000;

    protected LocationManager locationManager;
    static double n = 0;
    Long s1, r1;
    double plat, plon, clat, clon, dis;
    MyCount counter;
    Thread t1;
    EditText e1;
    boolean bool = true;

    Button b1, b2, b3, b4, b5;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button1); //current position
        b2 = (Button) findViewById(R.id.button2);// start moving.. calculates distance on clicking this
        b3 = (Button) findViewById(R.id.button3);// pause
        b4 = (Button) findViewById(R.id.button4);// resume
        b5 = (Button) findViewById(R.id.button5);//get distance
        e1 = (EditText) findViewById(R.id.editText);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                (android.location.LocationListener) new MyLocationListener()
        );
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCurrentLocation();
            }
        });

    }

    protected void showCurrentLocation() {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            clat = location.getLatitude();
            clon = location.getLongitude();
            Toast.makeText(test.this, message,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(test.this, "null location",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void start(View v) {

        switch (v.getId()) {

            case R.id.button2:
                t1 = new Thread();
                t1.start();
                counter = new MyCount(30000, 1000);
                counter.start();
                break;
            case R.id.button3:
                counter.cancel();
                bool = false;
                break;
            case R.id.button4:
                counter = new MyCount(s1, 1000);
                counter.start();
                bool = true;
                break;
            case R.id.button5:

                double time = n * 30 + r1;
                Toast.makeText(test.this, "distance in metres:" + String.valueOf(dis) + "Velocity in m/sec :" + String.valueOf(dis / time) + "Time :" + String.valueOf(time), Toast.LENGTH_LONG).show();

        }


    }


    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );

            Toast.makeText(test.this, message, Toast.LENGTH_LONG).show();
        }

        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(test.this, "Provider status changed",
                    Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(test.this,
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }

        public void onProviderEnabled(String s) {
            Toast.makeText(test.this,
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }

    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            counter = new MyCount(30000, 1000);
            counter.start();
            n = n + 1;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            s1 = millisUntilFinished;
            r1 = (30000 - s1) / 1000;
            e1.setText(String.valueOf(r1));


        }
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double latA = Math.toRadians(lat1);
        double lonA = Math.toRadians(lon1);
        double latB = Math.toRadians(lat2);
        double lonB = Math.toRadians(lon2);
        double cosAng = (Math.cos(latA) * Math.cos(latB) * Math.cos(lonB-lonA)) +
                (Math.sin(latA) * Math.sin(latB));
        double ang = Math.acos(cosAng);
        double dist = ang *6371;
        return dist;
    }

    @Override
    public void run() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        while (bool) {
            clat = location.getLatitude();
            clon = location.getLongitude();
            if (clat != plat || clon != plon) {
                dis += getDistance(plat, plon, clat, clon);
                plat = clat;
                plon = clon;

            }

        }


    }
}
