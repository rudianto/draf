package com.pamon.app.pamon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pamon.app.pamon.fragment.FragmentPrivateA;
import com.pamon.app.pamon.fragment.FragmentPrivateB;
import com.pamon.app.pamon.fragment.FragmentPrivateC;
import com.pamon.app.pamon.fragment.FragmentPrivateD;


/**
 * Created by Sofiyanudin on 05/11/2017.
 */

public class AdapterViewPagerPrivate extends FragmentPagerAdapter {
    FragmentPrivateA fragmentPrivateA;
    FragmentPrivateB fragmentPrivateB;
    FragmentPrivateC fragmentPrivateC;
    FragmentPrivateD fragmentPrivateD;
    public AdapterViewPagerPrivate(FragmentManager fm) {
        super(fm);
        fragmentPrivateA = new FragmentPrivateA();
        fragmentPrivateB = new FragmentPrivateB();
        fragmentPrivateC = new FragmentPrivateC();
        fragmentPrivateD = new FragmentPrivateD();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(fragmentPrivateA == null) fragmentPrivateA = new FragmentPrivateA();
                return fragmentPrivateA;

            case 1:
                if(fragmentPrivateB == null) fragmentPrivateB = new FragmentPrivateB();
                return fragmentPrivateB;

            case 2:
                if(fragmentPrivateC == null) fragmentPrivateC = new FragmentPrivateC();
                return fragmentPrivateC;

            case 3:
                if(fragmentPrivateD == null) fragmentPrivateD = new FragmentPrivateD();
                return fragmentPrivateD;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
