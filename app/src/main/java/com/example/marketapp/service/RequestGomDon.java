package com.example.marketapp.service;

public class RequestGomDon {

    private Collect collect;
    private Detail detail;

    public RequestGomDon(String c1, String c2, String d1) {
        this.collect = new Collect(c1,c2);
        this.detail = new Detail(d1);
    }

    public Collect getCollect() {
        return collect;
    }

    public void setCollect(Collect collect) {
        this.collect = collect;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public class Collect{
        private String idStore;
        private String location;

        public Collect(String idStore, String location) {
            this.idStore = idStore;
            this.location = location;
        }

        public String getIdStore() {
            return idStore;
        }

        public String getLocation() {
            return location;
        }
    }
    public class Detail{
        public Detail(String idBill) {
            this.idBill = idBill;
        }

        private String idBill;

        public String getIdBill() {
            return idBill;
        }
    }

}
