package com.adgvit.appathon2k18.appathon2k18;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Shashank Gutgutia on 01-03-2018.
 */

public class TimelinePageAdapter extends FragmentStatePagerAdapter {

    public TimelinePageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TimelineFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(TimelineFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
