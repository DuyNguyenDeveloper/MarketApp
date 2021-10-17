package com.example.marketapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketapp.R;
import com.example.marketapp.adpater.AdapterOrder;
import com.example.marketapp.models.Cart;
import com.example.marketapp.server.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyOderActivity extends AppCompatActivity {
    ListView rcvOrder;
    AdapterOrder adapterOrder;
    RequestQueue queue ;
    ArrayList<Cart> arrayList;
    TextView tvTongTien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_oder);
        queue =  Volley.newRequestQueue(this);
        rcvOrder = findViewById(R.id.rcvListMyOrder);
        tvTongTien = findViewById(R.id.tvTongTien);
        arrayList = new ArrayList<Cart>();
        getIndexCart();
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
                });
        queue.add(jsonObjectRequest);
    }
}