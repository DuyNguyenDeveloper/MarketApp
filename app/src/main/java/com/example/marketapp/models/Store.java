package com.example.marketapp.models;

import java.util.ArrayList;

public class Store {
    String id;
    String name;
    String site_code;
    String ward_id;
    ArrayList<StoreProduct> storeProduct;

    public Store() {
    }

    public ArrayList<StoreProduct> getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(ArrayList<StoreProduct> storeProduct) {
        this.storeProduct = storeProduct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite_code() {
        return site_code;
    }

    public void setSite_code(String site_code) {
        this.site_code = site_code;
    }

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }


    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", site_code='" + site_code + '\'' +
                ", ward_id='" + ward_id + '\'' +
                ", storeProduct=" + storeProduct +
                '}';
    }
}
