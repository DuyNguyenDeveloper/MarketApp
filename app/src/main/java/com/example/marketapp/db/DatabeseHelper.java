package com.example.marketapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabeseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "marketapp2";
    public static final int VERSION_DB = 3;

    public DatabeseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_sp = "CREATE TABLE sanPhamYeuThich(id text PRIMARY KEY,id_group text,p_name text,p_price text)";
        db.execSQL(sql_sp);
        String sql_spDaMua = "CREATE TABLE sanPhamDaMua(id text PRIMARY KEY,id_group text,p_name text,p_price text)";
        db.execSQL(sql_spDaMua);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists sql_sp");
        db.execSQL("DROP table if exists sql_spDaMua");
        onCreate(db);
    }
}
