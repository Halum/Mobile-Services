package com.halum.mobileservices;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static com.halum.mobileservices.Constants.*;

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
        if(layoutName.equals(GP))
            fragment.layout = R.layout.grameenphone;
        else if(layoutName.equals(AIRTEL))
            fragment.layout = R.layout.airtel;
        else if(layoutName.equals(BLINK))
            fragment.layout = R.layout.banglalink;
        else if(layoutName.equals(ROBI))
            fragment.layout = R.layout.robi;
        else if(layoutName.equals(TTALK))
            fragment.layout = R.layout.teletalk;
        else if(layoutName.equals(BANKS))
            fragment.layout = R.layout.banks;

        return fragment;
    }

    public OperatorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tabContent = inflater.inflate(this.layout, null);
        return tabContent;
    }
}
