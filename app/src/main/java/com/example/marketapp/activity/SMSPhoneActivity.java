package com.example.marketapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;

public class SMSPhoneActivity extends AppCompatActivity {

    TextView tvSMSEmail;
    Button btnPhoneSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsphone);

        tvSMSEmail = (TextView) findViewById(R.id.tv_sms_email);
        tvSMSEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SMSPhoneActivity.this, VerifyYourEmailActivity.class));
                finish();
            }
        });

        btnPhoneSMS = (Button) findViewById(R.id.btn_phone_sms);
        btnPhoneSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SMSPhoneActivity.this, SuccessVerifyActivity.class));
                finish();
            }
        });
    }
}