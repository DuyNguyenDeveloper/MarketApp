package com.example.marketapp.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketapp.R;
import com.example.marketapp.interfaceApp.RecyclerViewClickInterface;
import com.example.marketapp.models.StoreM;

import java.util.List;

public class FindStoreAdapter extends RecyclerView.Adapter<FindStoreAdapter.ViewHolder>{
    Context context;
    List<StoreM> storeList;

    private RecyclerViewClickInterface recyclerViewClickInterface;

    public FindStoreAdapter(Context context, List<StoreM> storeList, RecyclerViewClickInterface clickItem) {
        this.context = context;
        this.storeList = storeList;
        this.recyclerViewClickInterface = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoreM storeM = storeList.get(position);

        try {
            holder.tvIdStore.setText("ID Store: "+ storeM.getId());
            holder.tvStoreName.setText(storeM.getName());
            holder.tvWard.setText("ID Ward: " + storeM.getWard_id());
        }catch (Exception e){
            Log.e("ERROR : ", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

//    public void updateList(List<StoreM> list){
//        storeList = list;
//        notifyDataSetChanged();
//        if (list.size() == 0){
//            Toast.makeText(context, "Cửa hàng đang được cập nhật\nXin vui lòng chọn cửa hàng khác!", Toast.LENGTH_SHORT).show();
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvStoreName, tvIdStore, tvSiteCode, tvWard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            tvIdStore = itemView.findViewById(R.id.tvIdStore);
            tvWard = itemView.findViewById(R.id.tvWard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
