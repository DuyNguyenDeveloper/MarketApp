package com.example.marketapp.models;

import java.util.List;

public class StoreWard {
    private int id;
    private int district_id;
    private String name_ward;
    private List<StoreM> stores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getName_ward() {
        return name_ward;
    }

    public void setName_ward(String name_ward) {
        this.name_ward = name_ward;
    }

    public List<StoreM> getStores() {
        return stores;
    }

    public void setStores(List<StoreM> stores) {
        this.stores = stores;
    }
}
