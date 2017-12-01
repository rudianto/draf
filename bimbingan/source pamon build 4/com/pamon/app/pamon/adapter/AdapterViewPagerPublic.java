package com.pamon.app.pamon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pamon.app.pamon.fragment.FragmentPublicA;
import com.pamon.app.pamon.fragment.FragmentPublicB;


/**
 * Created by Sofiyanudin on 15/11/2017.
 */

public class AdapterViewPagerPublic extends FragmentPagerAdapter {
    FragmentPublicA fragmentPublicA;
    FragmentPublicB fragmentPublicB;
    public AdapterViewPagerPublic(FragmentManager fm) {
        super(fm);
        fragmentPublicA = new FragmentPublicA();
        fragmentPublicB = new FragmentPublicB();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(fragmentPublicA == null) fragmentPublicA = new FragmentPublicA();
                return fragmentPublicA;


            case 1:

                if(fragmentPublicB == null) fragmentPublicB = new FragmentPublicB();
                return fragmentPublicB;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
