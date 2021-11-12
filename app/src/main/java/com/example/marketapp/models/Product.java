package com.example.marketapp.models;

public class Product {
    String id;
    String id_group;
    String p_code;
    String p_name;
    String slug;
    String p_dvt;
    String p_price;
    String p_description;
    String p_thumbnail;

    public Product() {
    }

    public Product(String id, String id_group, String p_code, String p_name, String slug, String p_dvt, String p_price, String p_description, String p_thumbnail) {
        this.id = id;
        this.id_group = id_group;
        this.p_code = p_code;
        this.p_name = p_name;
        this.slug = slug;
        this.p_dvt = p_dvt;
        this.p_price = p_price;
        this.p_description = p_description;
        this.p_thumbnail = p_thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_group() {
        return id_group;
    }

    public void setId_group(String id_group) {
        this.id_group = id_group;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
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

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public String getP_thumbnail() {
        return p_thumbnail;
    }

    public void setP_thumbnail(String p_thumbnail) {
        this.p_thumbnail = p_thumbnail;
    }

    @Override
    public String toString() {
        return "product{" +
                "id:'" + id + '\'' +
                ", id_group:'" + id_group + '\'' +
                ", p_code:'" + p_code + '\'' +
                ", p_name:'" + p_name + '\'' +
                ", slug:'" + slug + '\'' +
                ", p_dvt:'" + p_dvt + '\'' +
                ", p_price:'" + p_price + '\'' +
                ", p_description:'" + p_description + '\'' +
                ", p_thumbnail:'" + p_thumbnail + '\'' +
                '}';
    }
}