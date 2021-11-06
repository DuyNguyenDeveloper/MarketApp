package com.example.marketapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.models.LoginResponse;
import com.example.marketapp.service.CallApi;
import com.example.marketapp.service.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignup, tvForgot;
    ProgressBar progressBar;
    private Button btnLogin;
    private EditText edEmail, edPassword;
    private SharedPreferences pref;
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");

        if(checkbox.equals("true")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        }else if(checkbox.equals("false")){
            Toast.makeText(this,"Please Sign In",Toast.LENGTH_SHORT).show();
        }

        remember = findViewById(R.id.rememberMe);


        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"Checked",Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"Unchecked",Toast.LENGTH_SHORT).show();
                }


            }
        });

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
                    writeToken(token);
                    Constants.ACCESS_TOKEN=token;
                    Log.e("Login ",Constants.ACCESS_TOKEN);
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

    private void writeToken(String token) {
        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("SEND_TOKEN", LoginActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("TOKEN", token);
        editor.commit();
    }

    public boolean isValidEmail(CharSequence target) {
        return (TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
