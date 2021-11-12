package com.example.marketapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;

public class VerifyYourEmailActivity extends AppCompatActivity {

    TextView tvVerifyPhone;
    Button btnSMSEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_your_email);


        tvVerifyPhone = (TextView) findViewById(R.id.tv_verify_phone);
        tvVerifyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyYourEmailActivity.this, VerifyYourPhoneActivity.class));
                finish();
            }
        });

        btnSMSEmail = (Button) findViewById(R.id.btn_sms_email);
        btnSMSEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyYourEmailActivity.this, SMSEmailActivity.class));
                finish();
            }
        });
    }
}