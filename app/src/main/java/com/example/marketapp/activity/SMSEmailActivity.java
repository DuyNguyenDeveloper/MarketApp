package com.example.marketapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;
import com.example.marketapp.models.ResponseRegister;
import com.example.marketapp.service.CallApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMSEmailActivity extends AppCompatActivity {

    TextView tvSMSPhone;
    Button btnEmailSMS;

    EditText edSmsEmail1, edSmsEmail2, edSmsEmail3, edSmsEmail4, edSmsEmail5, edSmsEmail6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsemail);

        edSmsEmail1 = findViewById(R.id.et_sms_em_1);
        edSmsEmail2 = findViewById(R.id.et_sms_em_2);
        edSmsEmail3 = findViewById(R.id.et_sms_em_3);
        edSmsEmail4 = findViewById(R.id.et_sms_em_4);
        edSmsEmail5 = findViewById(R.id.et_sms_em_5);
        edSmsEmail6 = findViewById(R.id.et_sms_em_6);

        tvSMSPhone = (TextView) findViewById(R.id.tv_sms_phone);
        tvSMSPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SMSEmailActivity.this, VerifyYourPhoneActivity.class));
//                finish();
            }
        });

        btnEmailSMS = (Button) findViewById(R.id.btn_email_config_sms);
        btnEmailSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyEmail();
//                startActivity(new Intent(SMSEmailActivity.this, SuccessVerifyActivity.class));
//                finish();
            }
        });
    }

    public boolean checkValidEmail(){
        String email1 = edSmsEmail1.getText().toString();
        String email2 = edSmsEmail2.getText().toString();
        String email3 = edSmsEmail3.getText().toString();
        String email4 = edSmsEmail4.getText().toString();
        String email5 = edSmsEmail5.getText().toString();
        String email6 = edSmsEmail6.getText().toString();

        if (email1.isEmpty()){
            edSmsEmail1.setError("Không được để trống");
            edSmsEmail1.requestFocus();
            return false;
        }
        if (email2.isEmpty()){
            edSmsEmail2.setError("Không được để trống");
            edSmsEmail2.requestFocus();
            return false;
        }
        if (email3.isEmpty()){
            edSmsEmail3.setError("Không được để trống");
            edSmsEmail3.requestFocus();
            return false;
        }
        if (email4.isEmpty()){
            edSmsEmail4.setError("Không được để trống");
            edSmsEmail4.requestFocus();
            return false;
        }
        if (email5.isEmpty()){
            edSmsEmail5.setError("Không được để trống");
            edSmsEmail5.requestFocus();
            return false;
        }
        if (email6.isEmpty()){
            edSmsEmail6.setError("Không được để trống");
            edSmsEmail6.requestFocus();
            return false;
        }
        return true;
    }

    private void verifyEmail() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        if (checkValidEmail()){
            String otp =
                    edSmsEmail1.getText().toString().trim() +
                            edSmsEmail2.getText().toString().trim() +
                            edSmsEmail3.getText().toString().trim() +
                            edSmsEmail4.getText().toString().trim() +
                            edSmsEmail5.getText().toString().trim() +
                            edSmsEmail6.getText().toString().trim();
            int otpConfig = Integer.parseInt(otp);
            CallApi.callApi.postActiveEmail(email, otpConfig).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                    try {
                        startActivity(new Intent(SMSEmailActivity.this, SuccessVerifyActivity.class));
                        Toast.makeText(getApplicationContext(), "Email activated!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Email Code Failed!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Call Api error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}