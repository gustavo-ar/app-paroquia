package com.example.gustavoar.sgp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.gustavoar.sgp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocalizacaoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng mOrigem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mOrigem=new LatLng(-23.7178615,-46.7660134);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mOrigem,17.0f));
        mMap.addMarker(new MarkerOptions()
                .position(mOrigem)
                .title("Paróquia São Lucas Evangelista")
                .snippet("São Paulo"));
    }
}
