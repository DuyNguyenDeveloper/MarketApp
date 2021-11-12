package com.example.marketapp.models;

import java.util.List;

public class GetStoreF {
    private boolean success;
    private List<StoreDistrict> getStore;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<StoreDistrict> getGetStore() {
        return getStore;
    }

    public void setGetStore(List<StoreDistrict> getStore) {
        this.getStore = getStore;
    }
}
