package com.example.marketapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.db.SPDao;
import com.example.marketapp.fragment.FavoriteFragment;
import com.example.marketapp.models.ListImageGroupProduct;
import com.example.marketapp.models.Product;
import com.example.marketapp.models.StoreProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Han on 29/12/2016.
 */
public class FavoriteProduct extends BaseAdapter {
    Context context;
    ArrayList<Product> product;
    public FavoriteProduct(Context context, ArrayList<Product> product)
    {
        this.context = context;
        this.product = product;
    }
    @Override
    public int getCount() {
        return product.size();
    }
    @Override
    public Object getItem(int i) {
        return product.get(i);
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
        Product obj = product.get(i);
        viewHolder.tvName.setText(obj.getP_name());
        viewHolder.tvPrice.setText(obj.getP_price());
        viewHolder.btnAdd.setVisibility(View.INVISIBLE);
        viewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPDao dao = new SPDao(context);
                dao.del(obj.getId());
                FavoriteProduct adapter = new FavoriteProduct(context,dao.getAll());
                FavoriteFragment.listView.setAdapter(adapter);
            }
        });
        ListImageGroupProduct listImageGroupProduct = new ListImageGroupProduct();
        Picasso.get()
                .load(listImageGroupProduct.getUrlImg(Integer.parseInt(obj.getId_group())))
                .placeholder(R.drawable.back_ground)
                .resize(50,50)
                .into(viewHolder.img);
        return view;
    }
}
