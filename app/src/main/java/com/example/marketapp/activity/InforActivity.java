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
import com.example.marketapp.models.User;
import com.example.marketapp.server.Constants;
import com.example.marketapp.server.RequestInterface;
import com.example.marketapp.server.ServerRequest;
import com.example.marketapp.server.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InforActivity extends AppCompatActivity {
    EditText edtName, edtBd, edtEmail, edtNumberP, edtAddress;
    ImageView ivBack;
    Button btnUpdate;
    TextView tvCp, tvTaiKhoan;
    String tk;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        mapView();
        showDataUser();
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        btnUpdate.setOnClickListener(view -> {
            updateServer();
        });
    }
    public void mapView() {
        edtName = findViewById(R.id.edtName);
        edtBd = findViewById(R.id.edtBirthDay);
        edtEmail = findViewById(R.id.edtEmail);
        edtNumberP = findViewById(R.id.edtPhoneN);
        edtAddress = findViewById(R.id.edtAddress);
        btnUpdate = findViewById(R.id.btnUpdateInfor);
        ivBack = findViewById(R.id.ivBack);
        tvCp = findViewById(R.id.tvCp);
        tvTaiKhoan = findViewById(R.id.tvTaiKhoan);
        tk = getIntent().getStringExtra("TK");
    }

    public void updateServer() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user = new User();
        user.setTaiKhoan(tk);
        user.setTenNguoiDung(edtName.getText().toString());
        user.setNgaySinh(edtBd.getText().toString());
        user.setEmail(edtEmail.getText().toString());
        user.setSdt(edtNumberP.getText().toString());
        user.setDiaChi(edtAddress.getText().toString());
        ServerRequest request = new ServerRequest();
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.updateInterface(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
//                Log.e("ia82", resp.getResult());
                if (resp.getResult().equals(Constants.SUCCESS)) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(Constants.TEN, resp.getUser().getTenNguoiDung());
                    editor.putString(Constants.NGAYSINH, resp.getUser().getNgaySinh());
                    editor.putString(Constants.EMAIL, resp.getUser().getEmail());
                    editor.putString(Constants.SDT, resp.getUser().getSdt());
                    editor.putString(Constants.DIACHI, resp.getUser().getDiaChi());
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.TAG, "failed");
            }
        });
    }

    public void showDataUser() {
        tvTaiKhoan.setText(tk);
        pref = getPreferences(0);


    }
}