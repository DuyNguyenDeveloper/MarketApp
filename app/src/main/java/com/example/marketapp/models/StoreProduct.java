package com.example.marketapp.models;

public class StoreProduct {
    String id;
    String id_product;
    String id_store;
    String p_number;
    Product products;

    public StoreProduct() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId_store() {
        return id_store;
    }

    public void setId_store(String id_store) {
        this.id_store = id_store;
    }

    public String getP_number() {
        return p_number;
    }

    public void setP_number(String p_number) {
        this.p_number = p_number;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "StoreProduct{" +
                "id='" + id + '\'' +
                ", id_product='" + id_product + '\'' +
                ", id_store='" + id_store + '\'' +
                ", p_number='" + p_number + '\'' +
                ", products=" + products +
                '}';
    }
}
