package com.example.marketapp.models;

public class Cart {
    private String id,id_product,name,qty,price,weight,slug,p_dvt,user_id;

    public Cart() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getP_dvt() {
        return p_dvt;
    }

    public void setP_dvt(String p_dvt) {
        this.p_dvt = p_dvt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", id_product='" + id_product + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", weight='" + weight + '\'' +
                ", slug='" + slug + '\'' +
                ", p_dvt='" + p_dvt + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
