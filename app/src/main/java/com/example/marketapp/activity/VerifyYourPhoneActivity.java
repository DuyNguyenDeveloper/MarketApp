package com.example.marketapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;

public class VerifyYourPhoneActivity extends AppCompatActivity {

    Button btnSMSNumber;
    TextView tvVerifyEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_your_phone);

        tvVerifyEmail = (TextView) findViewById(R.id.tv_verify_email);
        tvVerifyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyYourPhoneActivity.this, VerifyYourEmailActivity.class));
                finish();
            }
        });

        btnSMSNumber = (Button) findViewById(R.id.btn_sms_number);
        btnSMSNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyYourPhoneActivity.this, SMSPhoneActivity.class));
                finish();
            }
        });
    }
}