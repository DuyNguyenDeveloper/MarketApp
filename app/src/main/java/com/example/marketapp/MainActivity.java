package com.example.marketapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.marketapp.fragment.AccountFragment;
import com.example.marketapp.fragment.FavoriteFragment;
import com.example.marketapp.fragment.HomeFragment;
import com.example.marketapp.fragment.OffersFragment;
import com.example.marketapp.fragment.ShoppingFragment;
import com.example.marketapp.models.Cart;
import com.example.marketapp.models.DistricAndWard;
import com.example.marketapp.models.Store;
import com.example.marketapp.server.Constants;
import com.example.marketapp.server.VolleyCart;
import com.example.marketapp.server.VolleyProduct;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Store store;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE);
        Constants.ACCESS_TOKEN = sharedPreferences.getString("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9zaWV1dGhpLmNucHQudm5cL2FwaVwvbG9naW4iLCJpYXQiOjE2MzUxNTgyNjcsImV4cCI6MTYzNTE2MTg2NywibmJmIjoxNjM1MTU4MjY3LCJqdGkiOiJ0VWVvSUwwYTM2MXpQZlhIIiwic3ViIjozMCwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.oB0jKhWT0skoxlJmyeefRyuhWl3QiLOohS188sXILZY");
        Constants.ID_STORE = sharedPreferences.getString("ID_STORE","3801");
        Log.e("token",Constants.ACCESS_TOKEN);
        Log.e("ID_STORE",Constants.ID_STORE);
        VolleyProduct volleyProduct = new VolleyProduct(this);
        volleyProduct.getAllProductStore();





        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);
        //

        //
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case R.id.nav_offer:
                        fragment = new OffersFragment();
                        navigationView.getMenu().findItem(R.id.nav_offer).setChecked(true);
                        break;
                    case R.id.nav_shopping:
                        fragment = new ShoppingFragment();
                        navigationView.getMenu().findItem(R.id.nav_shopping).setChecked(true);
                        break;
                    case R.id.nav_favorite:
                        fragment = new FavoriteFragment();
                        navigationView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                        break;
                    case R.id.nav_account:
                        fragment = new AccountFragment();
                        navigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return false;
            }
        });
    }
}