package com.example.marketapp;

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
import com.example.marketapp.models.Cart;
import com.example.marketapp.models.Store;
import com.example.marketapp.server.VolleyCart;
import com.example.marketapp.server.VolleyProduct;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Store store;
    public static ArrayList<Cart> listCarts;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(store == null){
            store = new Store();
        }
        if(listCarts == null){
            listCarts = new ArrayList<Cart>();
        }
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