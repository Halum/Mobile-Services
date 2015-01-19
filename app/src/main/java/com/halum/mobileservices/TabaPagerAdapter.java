package com.halum.mobileservices;

/**
 * Created by Halum on 1/19/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabaPagerAdapter extends FragmentPagerAdapter {

    public TabaPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return OperatorFragment.newInstance(Constants.GP);
            case 1: return OperatorFragment.newInstance(Constants.Airtel);
            case 2: return OperatorFragment.newInstance(Constants.BLink);
            case 3: return OperatorFragment.newInstance(Constants.Robi);
            case 4: return OperatorFragment.newInstance(Constants.TTalk);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return Constants.GP;
            case 1: return Constants.Airtel;
            case 2: return Constants.BLink;
            case 3: return Constants.Robi;
            case 4: return Constants.TTalk;
        }
        return null;
    }
}
