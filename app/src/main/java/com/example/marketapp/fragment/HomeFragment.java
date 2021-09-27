package com.example.marketapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.marketapp.R;
import com.example.marketapp.activity.NotifiActivity;

public class HomeFragment extends Fragment {
    ImageView ivNotify;

    public HomeFragment() {
        // Required empty public constructor
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
        return view;
    }

    public void setClick() {
        ivNotify.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), NotifiActivity.class));
        });
    }

    public void mapView(View view) {
        ivNotify = view.findViewById(R.id.ivNotify);
    }
}