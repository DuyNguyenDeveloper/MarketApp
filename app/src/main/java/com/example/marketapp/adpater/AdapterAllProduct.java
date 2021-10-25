package com.example.marketapp.adpater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketapp.R;
import com.example.marketapp.db.SPDao;
import com.example.marketapp.models.ListImageGroupProduct;
import com.example.marketapp.models.Product;
import com.example.marketapp.models.StoreProduct;
import com.example.marketapp.server.VolleyCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Han on 29/12/2016.
 */
public class AdapterAllProduct extends BaseAdapter {
    Context context;
    ArrayList<StoreProduct> listProducts;
    public AdapterAllProduct(Context context, ArrayList<StoreProduct> listProducts)
    {
        this.context = context;
        this.listProducts = listProducts;
    }
    @Override
    public int getCount() {
        return listProducts.size();
    }
    @Override
    public Object getItem(int i) {
        return listProducts.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public static class ViewHolder {
        TextView tvName,tvPrice;
        Button btnAdd;
        ImageView img,imgFavorite;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_hide_product, null);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            viewHolder.btnAdd = (Button) view.findViewById(R.id.btnAdd);
            viewHolder.img = (ImageView) view.findViewById(R.id.img);
            viewHolder.imgFavorite = (ImageView) view.findViewById(R.id.imgFavorite);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        StoreProduct product = listProducts.get(i);
        viewHolder.tvName.setText(product.getProducts().getP_name());
        viewHolder.tvPrice.setText(product.getProducts().getP_price());
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyCart volleyCart = new VolleyCart(context);
                volleyCart.addCart(product.getProducts().getId());
                Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPDao dao = new SPDao(context);
                dao.add(product.getProducts());
                Toast.makeText(context, "Đã thêm vào mục yêu thích!", Toast.LENGTH_SHORT).show();
            }
        });
        ListImageGroupProduct listImageGroupProduct = new ListImageGroupProduct();
        Picasso.get()
                .load(listImageGroupProduct.getUrlImg(Integer.parseInt(product.getProducts().getId_group())))
                .placeholder(R.drawable.back_ground)
                .resize(50,50)
                .into(viewHolder.img);
        return view;
    }
}
