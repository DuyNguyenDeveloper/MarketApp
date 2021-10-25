package com.example.marketapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marketapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SPDao {
    SQLiteDatabase db;
    DatabeseHelper helper;
    public static final String TABLE_NAME = "sanPhamYeuThich";

    public SPDao(Context context) {
        helper = new DatabeseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<Product> getAll(){
        ArrayList<Product> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Product product = new Product();
            product.setId(c.getString(0));
            product.setId_group(c.getString(1));
            product.setP_name(c.getString(2));
            product.setP_price(c.getString(3));
            ls.add(product);
            c.moveToNext();
        }
        c.close();
        return ls;
    }
    public int add(Product product){
        ContentValues values = new ContentValues();
        values.put("id",product.getId());
        values.put("id_group",product.getId_group());
        values.put("p_name",product.getP_name());
        values.put("p_price",product.getP_price());
        try {
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("Product Dao add Error: ",ex.toString());
        }
        return 1;
    }
    public int del(String id){
        try {
            if (db.delete(TABLE_NAME,"id=?",new String[]{id})==0){
                return -1;
            }
        }catch (Exception ex){
            Log.e("Product Dao add Error: ",ex.toString());
        }
        return 1;
    }
}
