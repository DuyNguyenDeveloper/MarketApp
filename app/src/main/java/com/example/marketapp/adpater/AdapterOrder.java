package com.example.marketapp.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.marketapp.R;
import com.example.marketapp.models.Cart;
import com.example.marketapp.models.StoreProduct;
import com.example.marketapp.server.VolleyCart;

import java.util.ArrayList;

/**
 * Created by Han on 29/12/2016.
 */
public class AdapterOrder extends BaseAdapter {
    Context context;
    ArrayList<Cart> listCarts;
    public AdapterOrder(Context context, ArrayList<Cart> listCarts)
    {
        this.context = context;
        this.listCarts = listCarts;
    }
    @Override
    public int getCount() {
        return listCarts.size();
    }
    @Override
    public Object getItem(int i) {
        return listCarts.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public static class ViewHolder {
        TextView tvName,tvPrice,tvSL;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_my_order, null);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            viewHolder.tvSL = (TextView) view.findViewById(R.id.tvSL);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        Cart cart = listCarts.get(i);
        viewHolder.tvName.setText(cart.getName());
        viewHolder.tvPrice.setText(cart.getPrice());
        viewHolder.tvSL.setText(cart.getQty()+"");


        return view;
    }
}
