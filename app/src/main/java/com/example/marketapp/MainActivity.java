package com.example.marketapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPref = this.getSharedPreferences("SEND_ID_STORE", Context.MODE_PRIVATE);
        int idStore = sharedPref.getInt("ID_STORE", 0);
        Toast.makeText(getApplicationContext(), "" + idStore, Toast.LENGTH_SHORT).show();


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

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