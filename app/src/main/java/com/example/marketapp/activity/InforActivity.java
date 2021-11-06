package com.example.marketapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.R;
import com.example.marketapp.models.ResponseRegister;
import com.example.marketapp.models.UserRegister;
import com.example.marketapp.service.CallApi;
import com.example.marketapp.service.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforActivity extends AppCompatActivity {
    EditText edtLastname, edtFirstName, edtNumberP, edtAddress;
    Button btnUpdate;
    ImageView ivBack;
    TextView tvCp, tvTaiKhoan;
    String tk, lastName, firstName, address;
    int numberPhone;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        mapView();
        showDataUser();
        btnUpdate.setOnClickListener(view -> {
            updateServer();
        });
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    public void mapView() {
        edtLastname = findViewById(R.id.edtLastName);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtNumberP = findViewById(R.id.edtPhoneN);
        edtAddress = findViewById(R.id.edtAddress);
        btnUpdate = findViewById(R.id.btnUpdateInfor);
        ivBack = findViewById(R.id.ivBack);
        tvCp = findViewById(R.id.tvCp);
        tvTaiKhoan = findViewById(R.id.tvTaiKhoan);
        tk = getIntent().getStringExtra("TK");
        pref = getPreferences(0);
        String email = pref.getString("email", "");
        tvTaiKhoan.setText(email);
    }

    private boolean checkValid() {
        lastName = edtLastname.getText().toString().trim();
        firstName = edtFirstName.getText().toString().trim();
        try {
            numberPhone = Integer.parseInt(edtNumberP.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        address = edtAddress.getText().toString().trim();
        if (lastName.isEmpty()) {
            edtLastname.setError("Họ không được để trống");
            edtLastname.requestFocus();
            return false;
        }
        if (firstName.isEmpty()) {
            edtFirstName.setError("Tên không được để trống");
            edtFirstName.requestFocus();
            return false;
        }
        if (String.valueOf(numberPhone).length() < 9 || String.valueOf(numberPhone).length() > 14) {
            edtNumberP.setError("Số điện thoại không đúng định dạng");
            edtNumberP.requestFocus();
            return false;
        }
        if (address.isEmpty()) {
            edtAddress.setError("Địa chỉ không được để trống");
            edtAddress.requestFocus();
            return false;
        }
        return true;
    }

    private UserRegister getUser() {
        UserRegister user = new UserRegister();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPhoneNumber(numberPhone);
        user.setAddress(address);
        return user;
    }

    public void updateServer() {

        if (checkValid()) {
            CallApi.callApi.postUpdateUser(getUser(),"Bearer "+Constants.ACCESS_TOKEN).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                    if (!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                    try {
//                        finish();
                        if(response.body().getMessage().equals("Update successful!")){
                            Toast.makeText(getApplicationContext(), "Account successfully updated!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Account failed updated!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Log.d("ERROR", "" + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Call Api error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void showDataUser() {


    }
}