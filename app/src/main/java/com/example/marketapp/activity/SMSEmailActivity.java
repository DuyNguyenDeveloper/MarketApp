package com.example.marketapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;

public class SMSEmailActivity extends AppCompatActivity {

    TextView tvSMSPhone;
    Button btnEmailSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsemail);

        tvSMSPhone = (TextView) findViewById(R.id.tv_sms_phone);
        tvSMSPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SMSEmailActivity.this, VerifyYourPhoneActivity.class));
                finish();
            }
        });

        btnEmailSMS = (Button) findViewById(R.id.btn_sms_email);
        btnEmailSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SMSEmailActivity.this, SuccessVerifyActivity.class));
                finish();
            }
        });
    }
}