package com.example.davcpe.mylocation2;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;





import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView textView1;
    LocationClient mLocationClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

        boolean result = isServicesAvailable();

        result = isServicesAvailable();
        if (result) {
            mLocationClient = new LocationClient(this, mCallback, mListener);
        } else {
            finish();
        }
    }

    protected void onStart() {
        super.onStart();
        mLocationClient.connect();
    }

    protected void onStop() {
        super.onStop();
        mLocationClient.disconnect();
    }

    private boolean isServicesAvailable() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        return (resultCode == ConnectionResult.SUCCESS);
    }

    private GoogleApiClient.ConnectionCallbacks mCallback = new GoogleApiClient.ConnectionCallbacks() {
        public void onConnected(Bundle bundle) {
            Toast.makeText(MainActivity.this, "Services connected", Toast.LENGTH_SHORT).show();

            LocationRequest mRequest = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(5000).setFastestInterval(1000);

            mLocationClient.requestLocationUpdates(mRequest, locationListener);
        }

        public void onDisconnected() {
            Toast.makeText(MainActivity.this, "Services disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    private OnConnectionFailedListener mListener = new OnConnectionFailedListener() {
        public void onConnectionFailed(ConnectionResult result) {
            Toast.makeText(MainActivity.this, "Services connection failed", Toast.LENGTH_SHORT).show();
        }
    };

    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            textView1.setText("Provider : " + location.getProvider() + "\n"
                    + "Latitude : " + location.getLatitude() + "\n"
                    + "Longitude : " + location.getLongitude() + "\n"
                    + "Altitude : " + location.getAltitude() + "m\n"
                    + "Accuracy : " + location.getAccuracy() + "m\n"
                    + "Speed : " + location.getSpeed() + "m/s\n"
                    + "Bearing : " + location.getBearing() + "\n"
                    + "Time : " + location.getTime() + "ms");
        }
    };
}
