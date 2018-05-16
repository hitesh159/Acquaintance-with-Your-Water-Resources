package com.example.sarthak.ascs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {



    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new signIn();
        } else{
            return new signUp();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "SIGN IN";
            case 1:
                return "SIGN UP";

            default:
                return null;
        }
    }

}
