package com.example.shareonfoot.closet;

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
                return TabFragment_Clothes_inCloset.newInstance("private","share","small");
            case 1:
                return TabFragment_Clothes_inCloset.newInstance("private","음식","small");
            case 2:
                return TabFragment_Clothes_inCloset.newInstance("private","카페","small");
            case 3:
                return TabFragment_Clothes_inCloset.newInstance("private","디져트","small");
            case 4:
                return TabFragment_Clothes_inCloset.newInstance("private","놀거리","small");
            case 5:
                return TabFragment_Clothes_inCloset.newInstance("private","서점","small");
            case 6:
                return TabFragment_Clothes_inCloset.newInstance("private","미용실","small");
            case 7:
                return TabFragment_Clothes_inCloset.newInstance("private","포차","small");
            case 8:
                return TabFragment_Clothes_inCloset.newInstance("private","패션","small");
            case 9:
                return TabFragment_Clothes_inCloset.newInstance("private","기타","small");
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
