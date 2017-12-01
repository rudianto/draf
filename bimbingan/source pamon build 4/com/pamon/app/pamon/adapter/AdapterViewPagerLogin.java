package com.pamon.app.pamon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pamon.app.pamon.fragment.FragmentLoginA;
import com.pamon.app.pamon.fragment.FragmentLoginB;


/**
 * Created by Sofiyanudin on 04/11/2017.
 */

public class AdapterViewPagerLogin extends FragmentPagerAdapter {
    FragmentLoginA fragmentLoginA;
    FragmentLoginB fragmentLoginB;
    public AdapterViewPagerLogin(FragmentManager fm) {
        super(fm);
        fragmentLoginA = new FragmentLoginA();
        fragmentLoginB = new FragmentLoginB();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(fragmentLoginA == null) fragmentLoginA = new FragmentLoginA();
                return fragmentLoginA;


            case 1:

                if(fragmentLoginB == null) fragmentLoginB = new FragmentLoginB();

                return fragmentLoginB;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
