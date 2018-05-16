package com.example.sarthak.ascs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.sarthak.ascs.navigation_drawer.getResponseFromHttpUrl;

public class groundWaterLocations extends AppCompatActivity implements OnMapReadyCallback,
        MapboxMap.OnMapClickListener {

    Double latitude;
    Double longitude;
    Double category;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoiZGVtb25zd29yZCIsImEiOiJjamZkbWhreW00NzV5MzNwZGszaHQ0cTljIn0.OkcpnbPGIZgpzyA424LKKQ");
        Intent i=getIntent();
        Bundle b=i.getExtras();
        String s=b.getString("values");
        setContentView(R.layout.activity_ground_water_locations);
        Log.e("hh",s);
        try {
            jsonArray=new JSONArray(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       MapView mapview=(MapView)findViewById(R.id.mapView);
        mapview.onCreate(savedInstanceState);
        mapview.getMapAsync(this);

    }
    @Override
    public void onMapClick(@NonNull LatLng point) {

    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        IconFactory iconFactory = IconFactory.getInstance(this);
        com.mapbox.mapboxsdk.annotations.Icon icon1 = iconFactory.fromResource(R.drawable.icn1);
        com.mapbox.mapboxsdk.annotations.Icon icon2 = iconFactory.fromResource(R.drawable.icn2);
        com.mapbox.mapboxsdk.annotations.Icon icon3 = iconFactory.fromResource(R.drawable.icn3);
        com.mapbox.mapboxsdk.annotations.Icon icon4 = iconFactory.fromResource(R.drawable.icn1);
        String s="";
        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                if(Double.parseDouble(jsonArray.getJSONObject(i).getString("Category"))==1.0){
                    s="High Alert";
                    icon4=icon3;}
                else if(Double.parseDouble(jsonArray.getJSONObject(i).getString("Category"))==2.0) {
                    icon4 = icon2;
                    s="Medium level";
                }
                else if(Double.parseDouble(jsonArray.getJSONObject(i).getString("Category"))==3.0) {
                    icon4 = icon1;
                    s="Very good level";
                }
                } catch (JSONException e) {
                e.printStackTrace();
            }


            try {

                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(jsonArray.getJSONObject(i).getString("LAT")), Double.parseDouble(jsonArray.getJSONObject(i).getString("LON"))))
                            .title(s)
                            .icon(icon4)
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }
    }



    }


