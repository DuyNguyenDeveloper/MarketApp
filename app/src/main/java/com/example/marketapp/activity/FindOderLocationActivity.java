package com.example.marketapp.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;

import com.example.marketapp.R;
import com.example.marketapp.adpater.FindStoreAdapter;
import com.example.marketapp.models.StoreM;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindOderLocationActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap googleMapF;
    SupportMapFragment supportMapFragment;
    SearchView searchView;
    Button btnOrder;

    List<Address> addressList = null;
    String location;

    double latitude = 16.0471659;
    double longitude = 108.1716864;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_oder_location);

        //mapping
        searchView = findViewById(R.id.svLocation);
        btnOrder = findViewById(R.id.btnOrder);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                location = searchView.getQuery().toString();
                if (location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(FindOderLocationActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        Log.d("LOCATION1", addressList.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("LOCATION2", e.getMessage());
                    }
                    Address address = addressList.get(0);
                    latitude = address.getLatitude();
                    longitude = address.getLongitude();
                    Log.d("LOCATION", "Address: " + location + " " + "Latitude" + latitude + " " + "Longitude" + longitude);
                    LatLng latLng = new LatLng(latitude, longitude);
                    googleMapF.addMarker(new MarkerOptions().position(latLng).title(location));
                    googleMapF.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                location = searchView.getQuery().toString();
                filter(location);
                return false;
            }
        });
        supportMapFragment.getMapAsync(this);
    }

    private void filter(String text) {
        List<Address> temp = new ArrayList();
        Geocoder geocoder = new Geocoder(FindOderLocationActivity.this);
        try {
            addressList = geocoder.getFromLocationName(text, 1);
            Log.d("LOCATION1", addressList.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("LOCATION2", e.getMessage());
        }
        for (Address d : addressList) {
            if (d.getFeatureName().contains(text)) {
                latitude = d.getLatitude();
                longitude = d.getLongitude();
                temp.add(d);
            }
        }
        LatLng latLng = new LatLng(latitude, longitude);
        googleMapF.addMarker(new MarkerOptions().position(latLng).title(text));
        googleMapF.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMapF = googleMap;

        LatLng latLng = new LatLng(latitude, longitude);
        googleMapF.addMarker(new MarkerOptions().position(latLng).title("Da Nang"));
        googleMapF.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}