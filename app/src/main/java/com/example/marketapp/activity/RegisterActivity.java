package com.example.marketapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketapp.R;
import com.example.marketapp.models.ResponseRegister;
import com.example.marketapp.models.UserRegister;
import com.example.marketapp.service.CallApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView tvSignin;
    ProgressBar progressBar;
    private EditText edEmail, edPassword, edLastName, edFistName, edPhoneNumber, edAddress;
    private Button btRegister;

    String email, password, lastName, firstName, address;
    int phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        addClicks();
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        tvSignin = (TextView) findViewById(R.id.tv_signin);
        edEmail = findViewById(R.id.et_email);
        edPassword = findViewById(R.id.et_pass);
        edLastName = findViewById(R.id.et_lastName);
        edFistName = findViewById(R.id.et_fistName);
        edPhoneNumber = findViewById(R.id.et_phoneNumber);
        edAddress = findViewById(R.id.et_address);
        btRegister = findViewById(R.id.btn_register);
    }
    private void addClicks() {
        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private boolean checkValid(){
        email = edEmail.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        lastName = edLastName.getText().toString();
        firstName = edFistName.getText().toString();
        try {
            phoneNumber = Integer.parseInt(edPhoneNumber.getText().toString().trim());
        }catch (Exception e){
            e.printStackTrace();
        }
        address = edAddress.getText().toString();

        if (isValidEmail(email)){
            edEmail.setError("Email không hợp lệ");
            edEmail.requestFocus();
            return false;
        }
        if (password.length() < 6){
            edPassword.setError("Mật khẩu phải từ 6 ký tự trở lên");
            edPassword.requestFocus();
            return false;
        }
        if (lastName.isEmpty()){
            edLastName.setError("Họ không được để trống");
            edLastName.requestFocus();
            return false;
        }
        if (firstName.isEmpty()){
            edFistName.setError("Tên không được để trống");
            edFistName.requestFocus();
            return false;
        }
        if(String.valueOf(phoneNumber).length() < 9 || String.valueOf(phoneNumber).length() > 14){
            edPhoneNumber.setError("Số điện thoại không đúng định dạng");
            edPhoneNumber.requestFocus();
            return false;
        }
        if (address.isEmpty()){
            edAddress.setError("Địa chỉ không được để trống");
            edAddress.requestFocus();
            return false;
        }
        return true;
    }

    private UserRegister getUser(){
        UserRegister user = new UserRegister();
        user.setEmail(email);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        return user;
    }

    private void registerUser() {
        if (checkValid()){
            progressBar.setVisibility(View.VISIBLE);
            CallApi.callApi.postRegister(getUser()).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    progressBar.setVisibility(View.GONE);
                    if (!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                    try {
                        Intent intent = new Intent(RegisterActivity.this, SMSEmailActivity.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Log.d("ERROR", "" + t.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Call Api error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return (TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}