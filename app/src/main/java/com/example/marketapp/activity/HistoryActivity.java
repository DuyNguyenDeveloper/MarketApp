package com.example.marketapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.marketapp.R;
import com.example.marketapp.adpater.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HistoryActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mTablayout = findViewById(R.id.tab_layout);
        vPager = findViewById(R.id.v_pager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vPager.setAdapter(viewPagerAdapter);
        mTablayout.setupWithViewPager(vPager);
    }
}