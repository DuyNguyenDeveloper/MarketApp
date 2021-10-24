package com.example.marketapp.models;

import java.util.List;

public class StoreDistrictSub {
    private int id;
    private String name_district;
    private List<StoreWard> wards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_district() {
        return name_district;
    }

    public void setName_district(String name_district) {
        this.name_district = name_district;
    }

    public List<StoreWard> getWards() {
        return wards;
    }

    public void setWards(List<StoreWard> wards) {
        this.wards = wards;
    }
}
