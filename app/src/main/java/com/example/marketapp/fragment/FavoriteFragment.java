package com.example.marketapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.marketapp.R;
import com.example.marketapp.activity.NotifiActivity;
import com.example.marketapp.adpater.FavoriteProduct;
import com.example.marketapp.adpater.SliderAdapter;
import com.example.marketapp.db.SPDao;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    ImageView ivNotify;
    SliderView sliderView;
    public static ListView listView;
    SPDao dao;
    FavoriteProduct adapter;
    private Context context;
    int[] images = {
            R.mipmap.one,
            R.mipmap.three,
            R.mipmap.twon
    };

    public FavoriteFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        listView = view.findViewById(R.id.lvFavorite);
        mapView(view);
        setClick();
        slideView();
        dao = new SPDao(getContext());
        adapter = new FavoriteProduct(getContext(), dao.getAll());
        listView.setAdapter(adapter);
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