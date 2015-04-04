package com.example.davcpe.locationplot;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends FragmentActivity
        implements GooglePlayServicesClient.ConnectionCallbacks,
        com.google.android.gms.location.LocationListener,
        GooglePlayServicesClient.OnConnectionFailedListener{
    private GoogleMap myMap;            // map reference
    private PolylineOptions lineOptions = null;
    private LatLng point;
    private Button btn1;
    private TextView txtshowDistance,txtshowDetails,txtLat,txtLong;
    Location location;
    GPSTracker gpsTracker = new GPSTracker(MainActivity.this);



    Double a = 13.68714011267915;
    Double b = 100.53525868803263;
    LatLng startPosition = new LatLng(a, b);
    LatLng endPosition = new LatLng(13.683660045847258, 100.53900808095932);


    private LocationClient myLocationClient;
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    /**
     *     Activity's lifecycle event.
     *     onResume will be Called when the activity is starting.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy
                    = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        btn1 = (Button)findViewById(R.id.buttonNext);

        txtshowDistance =(TextView)findViewById(R.id.textView1);

        txtshowDetails = (TextView)findViewById(R.id.textView2);

        GMapV2Direction mp = new GMapV2Direction();
        Document doc = mp.getDocument(startPosition, endPosition, GMapV2Direction.MODE_DRIVING);
        String distance = mp.getDistanceText(doc);
        String duration = mp.getDurationText(doc);
        txtshowDistance.setText(duration);




        //On BTN1 Click
        btn1click();

        getMapReference();
    }
    //Get Address
    public String ConvertPointToLocation(String Latitude, String Longitude) {
        String address = "";
        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(
                    Float.parseFloat(Latitude), Float.parseFloat(Longitude), 1);

            if (addresses.size() > 0) {
                for (int index = 0; index < addresses.get(0)
                        .getMaxAddressLineIndex(); index++)
                    address += addresses.get(0).getAddressLine(index) + " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    /**
     *     Activity's lifecycle event.
     *     onResume will be called when the Activity receives focus
     *     and is visible
     */
    @Override
    protected  void onResume(){
        super.onResume();
        getMapReference();
        wakeUpLocationClient();
        myLocationClient.connect();
    }

    /**
     *      Activity's lifecycle event.
     *      onPause will be called when activity is going into the background,
     */
    @Override
    public void onPause(){
        super.onPause();
        if(myLocationClient != null){
            myLocationClient.disconnect();
        }
    }


    //Next to PageDetails
   private  void  btn1click(){
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent objIntent = new Intent(MainActivity.this,CheckDetails.class);
               startActivity(objIntent);
               finish();

           }
       });
   }






    /**
     *
     * @param lat - latitude of the location to move the camera to
     * @param lng - longitude of the location to move the camera to
     *            Prepares a CameraUpdate object to be used with  callbacks
     */
    private void gotoMyLocation(double lat, double lng) {
        changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(lat, lng))
                        .zoom(17.5f)
                        .bearing(0)
                        .tilt(25)
                        .build()
        ), new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                // Your code here to do something after the Map is rendered
            }

            @Override
            public void onCancel() {
                // Your code here to do something after the Map rendering is cancelled
            }
        });
    }

    /**
     *      When we receive focus, we need to get back our LocationClient
     *      Creates a new LocationClient object if there is none
     */
    private void wakeUpLocationClient() {
        if(myLocationClient == null){
            myLocationClient = new LocationClient(getApplicationContext(),
                    this,       // Connection Callbacks
                    this);      // OnConnectionFailedListener
        }
    }

    /**
     *      Get a map object reference if none exits and enable blue arrow icon on map
     */
    private void getMapReference() {
        if(myMap == null){
            myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

        }
        if(myMap != null){
            myMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * @param bundle
     *      LocationClient is connected
     */
    @Override
    public void onConnected(Bundle bundle) {
        myLocationClient.requestLocationUpdates(REQUEST,this); // LocationListener

    }

    /**
     *      LocationClient is disconnected
     */
    @Override
    public void onDisconnected() {

    }

    /**
     *
     * @param location - Location object with all the information about location
     *                 Callback from LocationClient every time our location is changed
     */
    @Override
    public void onLocationChanged(Location location) {
        gotoMyLocation(location.getLatitude(), location.getLongitude());
        ///////////////////////////////////////////////////////////////////////////////////////

        String stringLatitude = "", stringLongitude = "", nameOfLocation="";
        if (gpsTracker.canGetLocation()) {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
            nameOfLocation = ConvertPointToLocation(stringLatitude,stringLongitude);
            String strDetails = "lat : "+stringLatitude+"Long : "+stringLongitude+"Name of Location : "+nameOfLocation;
            txtshowDetails.setText(strDetails);

        }




        LatLng EndPoint = new LatLng(location.getLatitude(), location.getLongitude());
        //List all point
        lineOptions = new PolylineOptions();




    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {
        myMap.moveCamera(update);
    }
}

