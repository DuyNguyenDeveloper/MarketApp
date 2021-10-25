package com.example.marketapp.models;

import java.util.List;

public class StoreDistrict {
    private int id;
    private String name_district;
    private List<StoreDistrictSub> district;

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

    public List<StoreDistrictSub> getDistrict() {
        return district;
    }

    public void setDistrict(List<StoreDistrictSub> district) {
        this.district = district;
    }
}
