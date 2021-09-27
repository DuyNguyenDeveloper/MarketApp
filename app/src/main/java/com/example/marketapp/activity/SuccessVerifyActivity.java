package com.example.marketapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;

public class SuccessVerifyActivity extends AppCompatActivity {

    Button btnSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_verify);

        btnSuccess = (Button) findViewById(R.id.btn_success);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuccessVerifyActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}