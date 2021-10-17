package com.example.marketapp.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
        StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.BASE_URL_ADD_CART,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Error.Response", response.toString());
                        Cart cart = new Cart();
                        cart.setId_product(id_Product);
                        TestActivity.listCartsNow.add(cart);
                        kt = true;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id_Product);
                return params;
            }
        };
        queue.add(postRequest);
        return kt;
    }
}
