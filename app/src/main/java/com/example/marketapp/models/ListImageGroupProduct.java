package com.example.marketapp.models;

import java.util.ArrayList;

public class ListImageGroupProduct {
    public ArrayList<ImgUrl> arrayList;
    ImgUrl obj;

    public class ImgUrl {
        int viTri;
        String url;

        public ImgUrl() {
        }

        public ImgUrl(int viTri, String url) {
            this.viTri = viTri;
            this.url = url;
        }

        public int getViTri() {
            return viTri;
        }

        public String getUrl() {
            return url;
        }
    }

    public ListImageGroupProduct() {
        arrayList = new ArrayList<ImgUrl>();
        obj = new ImgUrl(1, "https://media-cdn.laodong.vn/Storage/NewsPortal/2021/4/24/901999/0Db9f9b38b1779492006.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(2, "https://image-us.eva.vn/upload/2-2020/images/2020-04-07/1586229951-eae0d3d1fc38b26a5b469c1da8804264.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(3, "https://vinmec-prod.s3.amazonaws.com/images/20210408_232103_727773_an-thit-lon.max-800x800.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(4, "https://suckhoedoisong.qltns.mediacdn.vn/thumb_w/1200/324455921873985536/2021/7/22/avatar1626924648875-1626924649695673618407.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(5, "https://i.doanhnhansaigon.vn/2020/02/26/1-1582701543_750x0.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(6, "https://chonhangchuan.com/wp-content/uploads/2020/10/d%E1%BA%A7u-%C4%83n-gi%C3%A1-r%E1%BA%BB-logo-min-767x478.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(7, "https://cdn.tgdd.vn/Files/2019/10/24/1211885/duong-tinh-luyen-la-gi-co-nen-su-dung-duong-tinh-luyen-hay-khong-201910241522445701.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(8, "https://cdn.brvn.vn/news/480px/2016/9309_HatNem.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(9, "https://vnn-imgs-f.vgcloud.vn/2019/03/25/16/hau-qua-nghiem-trong-khi-ban-an-my-tom-lien-tuc-trong-1-thang-a.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(10, "https://vietpat.vn/wp-content/uploads/2020/12/thuc-pham-dong-hop.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(11, "https://maisonmando.com/wp-content/uploads/2020/08/cach-su-dung-bot-ngot.png");
        arrayList.add(obj);
        obj = new ImgUrl(12, "https://vinmec-prod.s3.amazonaws.com/images/20191028_101859_512845_muoi.max-800x800.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(13, "https://danhgianuocmam.com/wp-content/uploads/2019/09/nuoc-mam-masan-tinh-hoa-thuc-viet.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(14, "https://cdn.tgdd.vn/Files/2019/02/16/1150251/nuoc-tuong-va-xi-dau-co-giong-nhau-khong--2_700x450.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(15, "https://www.hoteljob.vn/files/Anh-HTJ-Hong/cong-thuc-lam-11-loai-nuoc-sot-thong-dung-15.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(16, "https://nguyenchat.com.vn/wp-content/uploads/2019/02/cach-pha-ca-phe-phin-khong-bi-chan-01.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(17, "https://image-us.eva.vn/upload/2-2019/images/2019-05-03/cach-lam-sua-chua-hoa-qua-ngon-tai-nha-cach-lam-sua-chua-hoa-qua-3-1556854456-365-width551height366.jpg");
        arrayList.add(obj);
        obj = new ImgUrl(18, "https://cdn.tgdd.vn/2020/12/content/2-800x500-15.jpg");
        arrayList.add(obj);

    }
    public String getUrlImg(int i){
        return arrayList.get(i-1).getUrl();
    }


}
