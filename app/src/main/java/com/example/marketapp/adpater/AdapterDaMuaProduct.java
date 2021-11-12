package com.example.marketapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketapp.R;
import com.example.marketapp.db.SPDao;
import com.example.marketapp.fragment.FavoriteFragment;
import com.example.marketapp.models.ListImageGroupProduct;
import com.example.marketapp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Han on 29/12/2016.
 */
public class AdapterDaMuaProduct extends  RecyclerView.Adapter<AdapterDaMuaProduct.ViewHolder> {
    Context context;
    ArrayList<Product> product;
    public AdapterDaMuaProduct(Context context, ArrayList<Product> product)
    {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.item_product_da_mua, parent, false);

        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product obj = product.get(position);
        holder.tvName.setText(obj.getP_name());
        ListImageGroupProduct listImageGroupProduct = new ListImageGroupProduct();
        Picasso.get()
                .load(listImageGroupProduct.getUrlImg(Integer.parseInt(obj.getId_group())))
                .placeholder(R.drawable.back_ground)
                .resize(50,50)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return product.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public ImageView img;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            img = itemView.findViewById(R.id.img);
        }
    }

}
