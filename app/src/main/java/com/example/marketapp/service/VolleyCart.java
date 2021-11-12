package com.example.marketapp.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketapp.MainActivity;
import com.example.marketapp.models.Cart;

import java.util.HashMap;
import java.util.Map;

public class VolleyCart {
    RequestQueue queue ;
    boolean kt = false;
    public VolleyCart(Context context) {
        queue = Volley.newRequestQueue(context);
    }
    public boolean addCart(String id_Product){
         kt = false;
         String id = "id="+id_Product;
        StringRequest postRequest = new StringRequest(Request.Method.GET, Constants.BASE_URL_ADD_CART+id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response.toString());
                        kt = true;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString()+id);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }
        };
        queue.add(postRequest);
        return kt;
    }
    public boolean updateCart(String id_Product,int qty,String id_store){
        kt = false;
        String id = "id="+id_Product+"&qty="+qty+"&id_store="+id_store;
        StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.BASE_URL_UPDATE_CART+id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response.toString());
                        kt = true;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString()+id);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }
        };
        queue.add(postRequest);
        return kt;
    }
    public boolean removeCart(String id_Product){
        kt = false;
        String id = "id="+id_Product;
        StringRequest postRequest = new StringRequest(Request.Method.GET, Constants.BASE_URL_REMOVE_CART+id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response.toString());
                        kt = true;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString()+id);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }
        };
        queue.add(postRequest);
        return kt;
    }
}
