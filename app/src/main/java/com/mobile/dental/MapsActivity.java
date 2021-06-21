package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in hyderabad and move the camera
        LatLng klinik = new LatLng(-6.285370135173857, 107.87622940076227);
        mMap.addMarker(new MarkerOptions().position(klinik).title("Terapis Gigi dan Mulut Pusakajaya"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(klinik));
        float zoomLevel = 21.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(klinik, zoomLevel));
    }
}