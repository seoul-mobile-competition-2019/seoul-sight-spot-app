package com.mobile.seoul.seoulstampapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mobile.seoul.seoulstampapplication.fragment.SightColorFragment;
import com.mobile.seoul.seoulstampapplication.fragment.SightInfoFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount;

    public ViewPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SightInfoFragment();
            case 1:
                return new SightColorFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
