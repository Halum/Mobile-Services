package com.halum.mobileservices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Halum on 1/19/2015.
 */

/**
 * A fragment containing a simple view.
 */
public class OperatorFragment extends Fragment {
    private int layout;

    public static OperatorFragment newInstance(String layoutName) {
        OperatorFragment fragment = new OperatorFragment();
        /*Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);*/
        if(layoutName.equals(Constants.GP))
            fragment.layout = R.layout.grameenphone;
        else if(layoutName.equals(Constants.Airtel))
            fragment.layout = R.layout.airtel;
        else if(layoutName.equals(Constants.BLink))
            fragment.layout = R.layout.banglalink;
        else if(layoutName.equals(Constants.Robi))
            fragment.layout = R.layout.robi;
        else if(layoutName.equals(Constants.TTalk))
            fragment.layout = R.layout.teletalk;
        
        return fragment;
    }

    public OperatorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(this.layout, container, false);
        return rootView;
    }
}
