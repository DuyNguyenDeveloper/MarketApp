package com.example.marketapp.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.marketapp.R;
import com.example.marketapp.adpater.FindStoreAdapter;
import com.example.marketapp.models.StoreM;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindOderLocationActivity extends FragmentActivity implements OnMapReadyCallback{
    GoogleMap googleMapF;
    SupportMapFragment supportMapFragment;
    SearchView searchView;
    Button btnOrder;

    List<Address> addressList = null;
    Geocoder geocoder;
    List<Address> temp;
    Marker marker;

    String location;
    String selectedAddress;

    double latitude1 = 16.0471659;
    double longitude1 = 108.1716864;

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
                    latitude1 = address.getLatitude();
                    longitude1 = address.getLongitude();
                    Log.d("LOCATION", "Address: " + location + " " + "Latitude" + latitude1 + " " + "Longitude" + longitude1);
                    LatLng latLng = new LatLng(latitude1, longitude1);
                    marker = googleMapF.addMarker(new MarkerOptions().position(latLng).title(location));
                    googleMapF.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                addressList.clear();
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
        temp = new ArrayList();
        geocoder = new Geocoder(FindOderLocationActivity.this);
        try {
            addressList = geocoder.getFromLocationName(text, 1);
            Log.d("LOCATION1", addressList.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("LOCATION2", e.getMessage());
        }
        for (Address d : addressList) {
            if (d.getFeatureName().contains(text)) {
                latitude1 = d.getLatitude();
                longitude1 = d.getLongitude();
                temp.add(d);
            }
        }
        LatLng latLng = new LatLng(latitude1, longitude1);
        googleMapF.addMarker(new MarkerOptions().position(latLng).title(text));
        googleMapF.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        temp.clear();
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMapF = googleMap;

        LatLng latLng = new LatLng(latitude1, longitude1);
        googleMapF.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

        googleMapF.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                latitude1 = latLng.latitude;
                longitude1 = latLng.longitude;
                onLocationChanged(latitude1, longitude1);
                getAddress(latitude1, longitude1);
                Log.d("LOCATION", "Address: " + location + " " + "Latitude" + latitude1 + " " + "Longitude" + longitude1);
            }
        });
    }

    private void getAddress(double mLat, double mLot){
        geocoder = new Geocoder(FindOderLocationActivity.this, Locale.getDefault());
        if (mLat != 0){
            try {
                temp = geocoder.getFromLocation(mLat, mLot, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (temp != null){
                String mAddress = temp.get(0).getAddressLine(0);
                selectedAddress = mAddress;
                if (mAddress != null){
                    MarkerOptions options = new MarkerOptions();

                    LatLng latLng2 = new LatLng(mLat, mLot);

                    options.position(latLng2).title(selectedAddress);

                    marker = googleMapF.addMarker(options);
                    marker.showInfoWindow();
                   String location2= latLng2.latitude+","+latLng2.longitude;
                    Log.e("test Map",location2);
                    btnOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MyOderActivity.locationOrder = location2;
                            finish();
                        }
                    });
                }else{
                    Toast.makeText(FindOderLocationActivity.this, "ERROR Clicked Location", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(FindOderLocationActivity.this, "ERROR Clicked Location", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(FindOderLocationActivity.this, "Please Clicked Location", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLocationChanged(double mLat2, double mLot2) {
        LatLng myActualLocation = new LatLng(mLat2, mLot2);

        if (marker != null){
            marker.remove();
        }
    }

}