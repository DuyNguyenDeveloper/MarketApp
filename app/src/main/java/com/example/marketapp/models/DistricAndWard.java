package com.example.marketapp.models;

import java.util.ArrayList;

public class DistricAndWard {
    public ArrayList<Distric> districs;

    public DistricAndWard() {
        districs = new ArrayList<>();
        Distric distric = new Distric();
        distric.setName("Quận Sơn Trà");
        ArrayList<Ward> wards = new ArrayList<Ward>();
        Ward ward = new Ward("Phường An Hải Bắc","1");
        wards.add(ward);
        ward = new Ward("Phường An Hải Đông","2");
        wards.add(ward);
        ward = new Ward("Phường An Hải Tây","3");
        wards.add(ward);
        ward = new Ward("Phường Mân Thái","4");
        wards.add(ward);
        ward = new Ward("Phường Nại Hiên Đông","5");
        wards.add(ward);
        ward = new Ward("Phường Phước Mỹ","6");
        wards.add(ward);
        ward = new Ward("Phường Thọ Quang","7");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);


        distric = new Distric();
        distric.setName("Quận Thanh Khê");
        wards = new ArrayList<Ward>();
        ward = new Ward("Phường  An Khê","8");
        wards.add(ward);
        ward = new Ward("Phường Chính Gián","9");
        wards.add(ward);
        ward = new Ward("Phường Hòa Khê","10");
        wards.add(ward);
        ward = new Ward("Phường Tam Thuận","11");
        wards.add(ward);
        ward = new Ward("Phường Thạc Gián","12");
        wards.add(ward);
        ward = new Ward("Phường Thanh Khê Đông","13");
        wards.add(ward);
        ward = new Ward("Phường Thanh Khê Tây","14");
        wards.add(ward);
        ward = new Ward("Phường Vĩnh Trung","15");
        wards.add(ward);
        ward = new Ward("Phường Xuân Hà","16");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);

        distric = new Distric();
        distric.setName("Quận Hải Châu");
        wards = new ArrayList<Ward>();
        ward = new Ward("Phường Bình Thuận","17");
        wards.add(ward);
        ward = new Ward("Phường Hải Châu 1","18");
        wards.add(ward);
        ward = new Ward("Phường Hải Châu 2","19");
        wards.add(ward);
        ward = new Ward("Phường Hòa Cường Bắc","20");
        wards.add(ward);
        ward = new Ward("Phường Hòa Cường Nam","21");
        wards.add(ward);
        ward = new Ward("Phường Hòa Thuận Đông","22");
        wards.add(ward);
        ward = new Ward("Phường Hòa Thuận Tây","23");
        wards.add(ward);
        ward = new Ward("Phường Nam Dương","24");
        wards.add(ward);
        ward = new Ward("Phường Phước Ninh","25");
        wards.add(ward);
        ward = new Ward("Phường Thạch Thang","26");
        wards.add(ward);
        ward = new Ward("Phường Thanh Bình","27");
        wards.add(ward);
        ward = new Ward("Phường Thuận Phước","28");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);


        distric = new Distric();
        distric.setName("Quận Cẩm Lệ");
        wards = new ArrayList<Ward>();
        ward = new Ward("Phường Hòa An","29");
        wards.add(ward);
        ward = new Ward("Phường Hòa Phá","30");
        wards.add(ward);
        ward = new Ward("Phường Hòa Thọ Đông","31");
        wards.add(ward);
        ward = new Ward("Phường Hòa Thọ Tây","32");
        wards.add(ward);
        ward = new Ward("Phường Hòa Xuân","33");
        wards.add(ward);
        ward = new Ward("Phường Khuê Trung","34");
        wards.add(ward);

        distric = new Distric();
        distric.setName("Quận Ngũ Hành Sơn");
        wards = new ArrayList<Ward>();
        ward = new Ward("Phường Hòa Hải","35");
        wards.add(ward);
        ward = new Ward("Phường Hòa Quý","36");
        wards.add(ward);
        ward = new Ward("Phường Khuê Mỹ","37");
        wards.add(ward);
        ward = new Ward("Phường Mỹ An","38");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);

        distric = new Distric();
        distric.setName("Quận Liên Chiểu");
        wards = new ArrayList<Ward>();
        ward = new Ward("Phường Hòa Khánh Bắc","39");
        wards.add(ward);
        ward = new Ward("Phường Hòa Khánh Nam","40");
        wards.add(ward);
        ward = new Ward("Phường Hòa Minh","41");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);

        distric = new Distric();
        distric.setName("Huyện Hòa Vang");
        wards = new ArrayList<Ward>();
        ward = new Ward("Xã Hòa Châu","42");
        wards.add(ward);
        ward = new Ward("Xã Hòa Phước","43");
        wards.add(ward);
        distric.setWards(wards);
        districs.add(distric);
    }

    public ArrayList<Distric> getDistrics() {
        return districs;
    }

    public void setDistrics(ArrayList<Distric> districs) {
        this.districs = districs;
    }

    public class Ward {
        private String name;
        private String vitri;

        public String getVitri() {
            return vitri;
        }

        public void setVitri(String vitri) {
            this.vitri = vitri;
        }

        public Ward(String name,String vitri) {
            this.name = name;
            this.vitri = vitri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class Distric {
        private String name;
        private ArrayList<Ward> wards;


        public Distric() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Ward> getWards() {
            return wards;
        }

        public void setWards(ArrayList<Ward> wards) {
            this.wards = wards;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}
