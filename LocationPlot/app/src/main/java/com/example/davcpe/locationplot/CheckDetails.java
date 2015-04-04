package com.example.davcpe.locationplot;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CheckDetails extends FragmentActivity {
    GoogleMap mMap;
    GMapV2Direction md;

    LatLng startPosition = new LatLng(13.687140112679154, 100.53525868803263);
    LatLng endPosition = new LatLng(13.683660045847258, 100.53900808095932);

    GPSTracker gpsTracker = new GPSTracker(CheckDetails.this);

    private TextView txtDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_details);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy
                    = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        md = new GMapV2Direction();
        mMap = ((SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng coordinates = new LatLng(13.685400079263206, 100.537133384495975);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));

        mMap.addMarker(new MarkerOptions().position(startPosition).title("Start"));
        mMap.addMarker(new MarkerOptions().position(endPosition).title("End"));

        Document doc = md.getDocument(startPosition
                , endPosition, GMapV2Direction.MODE_DRIVING);
        int duration = md.getDurationValue(doc);
        String distance = md.getDistanceText(doc);
        String start_address = md.getStartAddress(doc);
        String copy_right = md.getCopyRights(doc);

        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);

        for(int i = 0 ; i < directionPoint.size() ; i++) {
            rectLine.add(directionPoint.get(i));
        }

        mMap.addPolyline(rectLine);
        /////////////////////////////////////////////////////////////////////GPS Checking
        txtDetails = (TextView)findViewById(R.id.textViewDetails);
        String stringLatitude = "", stringLongitude = "", nameOfLocation="";
        if (gpsTracker.canGetLocation()) {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
            nameOfLocation = ConvertPointToLocation(stringLatitude,stringLongitude);
            String strDetails = "lat : "+stringLatitude+"Long : "+stringLongitude+"Name of Location : "+nameOfLocation;
            txtDetails.setText(strDetails);

        }


    }

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


}
