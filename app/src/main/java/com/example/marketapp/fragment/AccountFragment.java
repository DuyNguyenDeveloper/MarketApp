package com.example.marketapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.marketapp.R;
import com.example.marketapp.activity.AllBillActivity;
import com.example.marketapp.activity.FindStoreActivity;
import com.example.marketapp.activity.HistoryActivity;
import com.example.marketapp.activity.InforActivity;
import com.example.marketapp.activity.LoginActivity;


public class AccountFragment extends Fragment {
    LinearLayout lnEditProfile;
    Button btnLogout;
    TextView tvChose, tvAllBill, tvHistory;

    public AccountFragment() {
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        RatingBar ratingBar = view.findViewById(R.id.rating);
        ratingBar.setNumStars(5);
        mapView(view);
        clickSet();

        return view;
    }

    public void clickSet() {
        lnEditProfile.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), InforActivity.class));
        });
        btnLogout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
        tvAllBill.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), AllBillActivity.class));
        });
        tvHistory.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), HistoryActivity.class));
        });
        tvChose.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), FindStoreActivity.class));
        });
    }

    public void mapView(View view) {
        btnLogout = view.findViewById(R.id.btn_logout);
        lnEditProfile = view.findViewById(R.id.lnEditProfile);
        tvAllBill = view.findViewById(R.id.tvAllBill);
        tvHistory = view.findViewById(R.id.tvHistory);
        tvChose = view.findViewById(R.id.tvChose);
    }
}