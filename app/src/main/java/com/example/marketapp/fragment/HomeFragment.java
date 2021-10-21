package com.example.marketapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.marketapp.R;
import com.example.marketapp.activity.FindStoreActivity;
import com.example.marketapp.activity.NotifiActivity;
import com.example.marketapp.adpater.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    ImageView ivMeat,ivFish, ivVegetable, ivMilk, ivFruit, ivDryFood, ivFastFood, ivDrink, ivNotify,ivChose;
    String imageUri, imageUri1, imageUri2, imageUri3, imageUri4, imageUri5, imageUri6,imageUri7;
    SliderView sliderView;
    private Context context;
    int[] images = {
            R.mipmap.one,
            R.mipmap.three,
            R.mipmap.twon
    };
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mapView(view);
        setClick();
        slideView();
        slideCatalogue();
        return view;
    }

    public void setClick() {
        ivNotify.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), NotifiActivity.class));
        });
        ivChose.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), FindStoreActivity.class));
        });
        ivMeat.setOnClickListener(view -> {
            FragmentManager transaction = getFragmentManager();
            transaction.beginTransaction().replace(R.id.body_container,new ShoppingFragment()).commit();

        });
    }

    public void mapView(View view) {
        ivNotify = view.findViewById(R.id.ivNotify);
        ivChose = view.findViewById(R.id.ivChose);
        sliderView = view.findViewById(R.id.image_slider);
        ivMeat = view.findViewById(R.id.ivMeat);
        ivFish = view.findViewById(R.id.ivFish);
        ivVegetable = view.findViewById(R.id.ivVegetable);
        ivMilk = view.findViewById(R.id.ivMilk);
        ivFruit = view.findViewById(R.id.ivFruit);
        ivDryFood = view.findViewById(R.id.ivDryFood);
        ivFastFood = view.findViewById(R.id.ivFastFood);
        ivDrink = view.findViewById(R.id.ivDrink);
    }
    public void slideView() {
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
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
        imageUri7 = "https://images.unsplash.com/photo-1510130387422-82bed34b37e9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1935&q=80";
        Picasso.get().load(imageUri7).into(ivFish);
    }
}