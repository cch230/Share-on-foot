package com.example.shareonfoot.closet;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter_closet extends FragmentStatePagerAdapter {

    Fragment fragment;

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_closet(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        switch (position) {
            case 0:
                return TabFragment_Clothes_inCloset.newInstance("private","share");
            case 1:
                return TabFragment_Clothes_inCloset.newInstance("private","카페&디저트");
            case 2:
                return TabFragment_Clothes_inCloset.newInstance("private","음식");
            case 3:
                return TabFragment_Clothes_inCloset.newInstance("private","스포츠");
            case 4:
                return TabFragment_Clothes_inCloset.newInstance("private","독서&연극");
            case 5:
                return TabFragment_Clothes_inCloset.newInstance("private","포차");
            case 6:
                return TabFragment_Clothes_inCloset.newInstance("private","놀거리");
            case 7:
                return TabFragment_Clothes_inCloset.newInstance("private","포차");
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
