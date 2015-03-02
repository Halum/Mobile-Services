package com.halum.mobileservices;

/**
 * Created by Halum on 1/19/2015.
 */

import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import static com.halum.mobileservices.Constants.*;
/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return OperatorFragment.newInstance(ACTIVE_OPERATORS.get(position));
    }

    @Override
    public int getCount() {
        // number of tabs
        return ACTIVE_OPERATORS.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ACTIVE_OPERATORS.get(position);
    }

}



