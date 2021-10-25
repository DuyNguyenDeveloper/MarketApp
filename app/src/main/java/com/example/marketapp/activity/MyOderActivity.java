package com.example.marketapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketapp.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.adpater.AdapterOrder;
import com.example.marketapp.db.SPDaMuaDao;
import com.example.marketapp.models.Cart;
import com.example.marketapp.models.DistricAndWard;
import com.example.marketapp.models.Product;
import com.example.marketapp.service.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyOderActivity extends AppCompatActivity {
    public static ListView rcvOrder;
    public static AdapterOrder adapterOrder;
    RequestQueue queue ;
   public static ArrayList<Cart> arrayList;
    TextView tvTongTien;
    AppCompatButton btnThanhToan;
    ImageView imgBack,imgUpdateOrder;
    TextView tvName,tvPhone,tvQuan,tvPhuong,tvTo,tvDiaChi;
    AlertDialog.Builder builder;
    DistricAndWard districAndWard;
    public  ArrayList<DistricAndWard.Ward> wards;
    public int vitriDistric=0,vitriWards=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_oder);

        queue =  Volley.newRequestQueue(this);
        rcvOrder = findViewById(R.id.rcvListMyOrder);
        tvTongTien = findViewById(R.id.tvTongTien);
        arrayList = new ArrayList<Cart>();
        btnThanhToan = findViewById(R.id.btnThanhToan);
        tvName= findViewById(R.id.tvName);
        tvPhone= findViewById(R.id.tvPhone);
        tvQuan= findViewById(R.id.tvQuan);
        tvPhuong= findViewById(R.id.tvPhuong);
        tvTo= findViewById(R.id.tvTo);
        tvDiaChi= findViewById(R.id.tvDiaChi);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tvName.getText().toString().equals("")||tvPhone.getText().toString().equals("")||tvDiaChi.getText().toString().equals("")||
                        tvTo.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Không để trông các trường!", Toast.LENGTH_SHORT).show();
                }else{
                    thanhToan(tvName.getText().toString(),tvPhone.getText().toString(),tvDiaChi.getText().toString(),
                            tvTo.getText().toString(),vitriDistric,vitriWards, Constants.ID_STORE);
                }
            }
        });
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        imgUpdateOrder = findViewById(R.id.imgUpdateOrder);
        imgUpdateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        districAndWard = new DistricAndWard();
        getIndexCart();

    }
    public void alertDialog() {
        final EditText edName, edPhone,edTo,edDiaChi;
        final Spinner spDistric,spWard;
        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_order, null);
        builder.setView(v);
        edName = (EditText) v.findViewById(R.id.edName);
        edPhone = (EditText) v.findViewById(R.id.edPhone);
        spDistric = (Spinner) v.findViewById(R.id.spDistric);
        ArrayList<DistricAndWard.Distric> arrayList = districAndWard.getDistrics();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrayList);

        spDistric.setAdapter(adapter);
        spWard = (Spinner) v.findViewById(R.id.spWard);
        spDistric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 wards = districAndWard.getDistrics().get(i).getWards();
                ArrayAdapter adapter2 = new ArrayAdapter(MyOderActivity.this, android.R.layout.simple_spinner_item,wards);
                spWard.setAdapter(adapter2);
                vitriDistric = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vitriWards=Integer.parseInt(wards.get(i).getVitri());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edTo = (EditText) v.findViewById(R.id.edTo);
        edDiaChi = (EditText) v.findViewById(R.id.edDiaChi);
        edName.setText(tvName.getText());
        edPhone.setText(tvPhone.getText());
        edTo.setText(tvTo.getText());
        edDiaChi.setText(tvDiaChi.getText());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    tvName.setText(edName.getText());
                    tvPhone.setText(edPhone.getText());
                    tvTo.setText(edTo.getText());
                    tvDiaChi.setText(edDiaChi.getText());
                    tvQuan.setText(spDistric.getSelectedItem().toString());
                    tvPhuong.setText(spWard.getSelectedItem().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //click cancel
               //Toast.makeText(MainActivity.this, "Login that bai", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void getIndexCart(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Constants.BASE_URL_GET_INDEX_CART, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray shoppingcart = null;
                        Log.e("test2",response.toString());
                        try {
                            shoppingcart = response.getJSONArray("shoppingcart");
                            String tongTien = response.getString("total");
                            for (int i = 0; i <= shoppingcart.length() - 1; i++) {
                                JSONObject cartJson = shoppingcart.getJSONObject(i);
                                Cart cart = new Cart();
                                cart.setId(cartJson.getString("id"));
                                cart.setId_product(cartJson.getString("id_product"));
                                cart.setName(cartJson.getString("name"));
                                cart.setQty(cartJson.getString("qty"));
                                cart.setPrice(cartJson.getString("price"));
                                cart.setWeight(cartJson.getString("weight"));
                                cart.setSlug(cartJson.getString("slug"));
                                cart.setP_dvt(cartJson.getString("p_dvt"));
                                cart.setUser_id(cartJson.getString("user_id"));
                                arrayList.add(cart);
                            }
                            Log.e("test3",tongTien);
                            tvTongTien.setText("Tổng tiền: "+tongTien);
                            adapterOrder = new AdapterOrder(MyOderActivity.this,arrayList);
                            rcvOrder.setAdapter(adapterOrder);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }
    private void thanhToan(String name,String phone,String address,String to,int district,int ward,String store){
        String url = Constants.BASE_URL_ORDER+"name="+name+"&phone="+phone+"&address="+address+"&to="+to+"&district="+district+"&ward="+ward+"&store="+store;
        Log.e("",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("error",response.toString());

                            SPDaMuaDao dao = new SPDaMuaDao(MyOderActivity.this);
//                            Product obj;
//                            for (int i=0;i<=arrayList.size()-1;i++){
//                                obj = new Product();
//                                Cart cart = arrayList.get(i);
//                                obj.setId(cart.getId());
//                                obj.setId_group(cart.getId_product());
//                                obj.setP_name(cart.getName());
//                                obj.setP_price(cart.getPrice());
//                                dao.add(obj);
//                            }
                        Toast.makeText(getApplicationContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }

//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", name);
//                params.put("phone", phone);
//                params.put("address", address);
//                params.put("to", to);
//                params.put("district", district+"");
//                params.put("wrad", ward+"");
//                params.put("store", store);
//                return params;
//            }
        };
        queue.add(jsonObjectRequest);
    }
}