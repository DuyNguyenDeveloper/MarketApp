package com.example.marketapp.models;

public class StoreM {
    private int id;
    private String name;
    private String site_code;
    private int ward_id;

    public StoreM() {
    }

    public StoreM(int id, String name, String site_code, int ward_id) {
        this.id = id;
        this.name = name;
        this.site_code = site_code;
        this.ward_id = ward_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getWard_id() {
        return ward_id;
    }

    public void setWard_id(int ward_id) {
        this.ward_id = ward_id;
    }
}
