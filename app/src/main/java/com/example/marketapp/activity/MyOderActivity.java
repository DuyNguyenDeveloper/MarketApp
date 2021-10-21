package com.example.marketapp.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;

public class MyOderActivity extends AppCompatActivity {
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_oder);
        mapView();
        click();
    }
    private void click(){
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    private void mapView(){
        ivBack = findViewById(R.id.ivBack);
    }
}