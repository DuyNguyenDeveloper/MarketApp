package com.example.marketapp.adpater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.marketapp.fragment.CompletedApplicationFragment;
import com.example.marketapp.fragment.ConfirmedFragment;
import com.example.marketapp.fragment.DeliveryInProgressFragment;
import com.example.marketapp.fragment.ProcessingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProcessingFragment();
            case 1:
                return new ConfirmedFragment();
            case 2:
                return new DeliveryInProgressFragment();
            case 3:
                return new CompletedApplicationFragment();

            default:
                return new ProcessingFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= "";
        switch (position){
            case 0:
                title = "Đang Xử Lý";
                break;
            case 1:
                title = "Đã Hoàn Thành ";
                break;
            case 2:
                title = "Đang Giao Hàng ";
                break;
            case 3:
                title = "Hoàn Thành";
                break;
        }
        return title;
    }
}
