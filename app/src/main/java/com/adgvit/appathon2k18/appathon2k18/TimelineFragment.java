package com.adgvit.appathon2k18.appathon2k18;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shashank Gutgutia on 01-03-2018.
 */

public class TimelineFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timelayout, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.time)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
