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

import com.example.marketapp.R;
import com.example.marketapp.activity.NotifiActivity;
import com.example.marketapp.adpater.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class FavoriteFragment extends Fragment {
    ImageView ivNotify;
    SliderView sliderView;
    private Context context;
    int[] images = {
            R.mipmap.one,
            R.mipmap.three,
            R.mipmap.twon
    };
    public FavoriteFragment() {
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
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mapView(view);
        setClick();
        slideView();
        return view;
    }
    public void setClick() {
        ivNotify.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), NotifiActivity.class));
        });
    }
    public void mapView(View view) {
        ivNotify = view.findViewById(R.id.ivNotify);
        sliderView = view.findViewById(R.id.image_slider);
    }
    public void slideView() {
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}