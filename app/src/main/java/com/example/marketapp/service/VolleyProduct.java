package com.example.marketapp.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketapp.MainActivity;
import com.example.marketapp.models.Product;
import com.example.marketapp.models.Store;
import com.example.marketapp.models.StoreProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyProduct {
    RequestQueue requestQueue;
    Store stores;

    public VolleyProduct(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        stores = new Store();
    }

    public void getAllProductStore() {
        String url = Constants.BASE_URL_GET_ALL_PRODUCT_BY_IDSTORE+"site_code="+Constants.ID_STORE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("vp", response.toString());
                        JSONArray jsonStores = null;
                        try {
                            jsonStores = response.getJSONArray("getStore");
                            JSONObject onlyStore = jsonStores.getJSONObject(0);
                            stores.setId(onlyStore.getString("id"));
                            stores.setName(onlyStore.getString("name"));
                            stores.setSite_code(onlyStore.getString("site_code"));
                            stores.setWard_id(onlyStore.getString("ward_id"));
                            //storeProduct
                            ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
                            //vong lap lay storeProduct
                            JSONArray jsonStoreProduct = onlyStore.getJSONArray("storeProduct");

                            for (int i = 0; i <= jsonStoreProduct.length() - 1; i++) {

                                JSONObject onlyStoreProduct = jsonStoreProduct.getJSONObject(i);

                                StoreProduct storeProduct = new StoreProduct();
                                storeProduct.setId(onlyStoreProduct.getString("id"));
                                storeProduct.setId_product(onlyStoreProduct.getString("id_product"));
                                storeProduct.setId_store(onlyStoreProduct.getString("id_store"));
                                storeProduct.setP_number(onlyStoreProduct.getString("p_number"));

                                JSONObject onlyProduct = onlyStoreProduct.getJSONArray("products").getJSONObject(0);
                                Product product = new Product();
                                product.setId(onlyProduct.getString("id"));
                                product.setId_group(onlyProduct.getString("id_group"));
                                product.setP_code(onlyProduct.getString("p_code"));
                                product.setP_name(onlyProduct.getString("p_name"));
                                product.setSlug(onlyProduct.getString("slug"));
                                product.setP_dvt(onlyProduct.getString("p_dvt"));
                                product.setP_price(onlyProduct.getString("p_price"));
                                product.setP_description(onlyProduct.getString("p_description"));
                                product.setP_thumbnail(onlyProduct.getString("p_thumbnail"));
                                storeProduct.setProducts(product);
                                storeProducts.add(storeProduct);

                            }
                            stores.setStoreProduct(storeProducts);
                            MainActivity.store = stores;
                            Log.e("",MainActivity.store.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                }) {
            //send token
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "Bearer " + Constants.ACCESS_TOKEN);
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


}
