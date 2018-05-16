package com.example.sarthak.ascs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.View;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import static android.content.ContentValues.TAG;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class watermaps extends AppCompatActivity implements OnMapReadyCallback,
        MapboxMap.OnMapClickListener {
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoiZGVtb25zd29yZCIsImEiOiJjamZkbWhreW00NzV5MzNwZGszaHQ0cTljIn0.OkcpnbPGIZgpzyA424LKKQ");
        setContentView(R.layout.activity_watermaps);
        try {


            mapView = findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);

        }catch (InflateException e)
        {
            Log.e(TAG, "Inflate exception");
        }

    }

    @Override
    public void onMapClick(@NonNull LatLng point) {

    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(24.744, 76.99))
                .title("Eiffel Tower")
        );
    }
}
