package com.example.marketapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.marketapp.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.activity.MyOderActivity;
import com.example.marketapp.adpater.AdapterAllProduct;
import com.example.marketapp.models.ListImageGroupProduct;
import com.example.marketapp.models.StoreProduct;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShoppingFragment extends Fragment {
    ImageView ivMeat, ivVegetable, ivMilk, ivFruit, ivDryFood, ivFastFood, ivDrink;
    String imageUri, imageUri1, imageUri2, imageUri3, imageUri4, imageUri5, imageUri6;
    ExtendedFloatingActionButton extended_fab;

    private ArrayList<StoreProduct> listFilter;
    private Spinner spGroup;

    private ListView lvProduct;
    private AdapterAllProduct adapter;
    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        mapView(view);
        slideCatalogue();
        setClick();
        lvProduct = view.findViewById(R.id.lvAllProduct);

        listFilter = new ArrayList<>();
        spGroup = view.findViewById(R.id.spGroup);
        ListImageGroupProduct listImageGroupProduct = new ListImageGroupProduct();
        ArrayList arrayGroup = listImageGroupProduct.getArrayList();
        arrayGroup.add(0,"Tất cả");
        ArrayAdapter adapterGroup = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayGroup);
        spGroup.setAdapter(adapterGroup);
        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    loadListProduct(0,view);
                }else {
                    listFilter = new ArrayList<>();
                    for (StoreProduct obj:MainActivity.store.getStoreProduct()){
                        Log.e("test",i+obj.getProducts().getId_group());
                        if(obj.getProducts().getId_group().equals(i+"")){
                            listFilter.add(obj);
                        }
                    }
                    loadListProduct(1,view);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadListProduct(0,view);
        return view;
    }
    public void loadListProduct(int i,View view){
        if(i==0){
            adapter = new AdapterAllProduct(getContext(), MainActivity.store.getStoreProduct());
            lvProduct.setAdapter(adapter);
        }else {
            adapter = new AdapterAllProduct(getContext(), listFilter);
            lvProduct.setAdapter(adapter);
        }

    }
    public void setClick() {
        extended_fab.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MyOderActivity.class);
            startActivity(intent);
        });
    }

    public void mapView(View view) {
        ivMeat = view.findViewById(R.id.ivMeat);
        ivVegetable = view.findViewById(R.id.ivVegetable);
        ivMilk = view.findViewById(R.id.ivMilk);
        ivFruit = view.findViewById(R.id.ivFruit);
        ivDryFood = view.findViewById(R.id.ivDryFood);
        ivFastFood = view.findViewById(R.id.ivFastFood);
        ivDrink = view.findViewById(R.id.ivDrink);
        extended_fab = view.findViewById(R.id.extended_fab);
    }

    public void slideCatalogue() {
        imageUri = "https://images.unsplash.com/photo-1602470520998-f4a52199a3d6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80";
        Picasso.get().load(imageUri).into(ivMeat);
        imageUri1 = "https://images.unsplash.com/photo-1557844352-761f2565b576?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80";
        Picasso.get().load(imageUri1).into(ivVegetable);
        imageUri2 = "https://cdn.diabetesselfmanagement.com/2016/02/Shirley021616.jpg";
        Picasso.get().load(imageUri2).into(ivMilk);
        imageUri3 = "https://images.unsplash.com/photo-1610832958506-aa56368176cf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80";
        Picasso.get().load(imageUri3).into(ivFruit);
        imageUri4 = "https://media.istockphoto.com/photos/cardboard-box-filled-with-nonperishable-foods-on-wooden-table-high-picture-id1283712032";
        Picasso.get().load(imageUri4).into(ivDryFood);
        imageUri5 = "https://images.unsplash.com/photo-1512152272829-e3139592d56f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2070&q=80";
        Picasso.get().load(imageUri5).into(ivFastFood);
        imageUri6 = "https://cdn.tgdd.vn/2021/05/content/1-800x450-54.jpg";
        Picasso.get().load(imageUri6).into(ivDrink);

    }
}