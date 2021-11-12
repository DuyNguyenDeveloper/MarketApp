package com.example.marketapp.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;

public class NotifiActivity extends AppCompatActivity {
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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