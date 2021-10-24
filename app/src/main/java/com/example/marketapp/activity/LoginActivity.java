package com.example.marketapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.marketapp.models.LoginResponse;
import com.example.marketapp.service.CallApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignup, tvForgot;
    ProgressBar progressBar;
    private Button btnLogin;
    private EditText edEmail, edPassword;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvForgot = (TextView) findViewById(R.id.tv_forgot);

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                finish();
            }
        });

        initViews();
        addClicks();
    }

    private void initViews() {
        tvSignup = (TextView) findViewById(R.id.tv_register);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        btnLogin = findViewById(R.id.btn_login_em);
        edEmail = findViewById(R.id.et_email_login);
        edPassword = findViewById(R.id.et_password_login);
        pref = getPreferences(0);
    }
    private void addClicks() {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(new Intent(LoginActivity.this, RegisterActivity.class));
                startActivity(i);
            }
        });
        //test123
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogin();
//                startActivity(new Intent(LoginActivity.this, FindStoreActivity.class));
//                finish();
            }
        });
    }

    private void postLogin() {
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        if (isValidEmail(email)){
            edEmail.setError("Email không hợp lệ");
            edEmail.requestFocus();
            return;
        }
        if (password.length() < 6){
            edPassword.setError("Mật khẩu phải từ 6 ký tự trở lên");
            edPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        CallApi.callApi.postLogin(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                try {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    Intent intent = new Intent(LoginActivity.this, FindStoreActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Call Api error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isValidEmail(CharSequence target) {
        return (TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
