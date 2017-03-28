package com.appcloud.frankiappforyoigo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appcloud.frankiappforyoigo.Fragments.TarifasCombinadasFragment;
import com.appcloud.frankiappforyoigo.Fragments.TarifasMovilesFragment;

/**
 * Created by cristian on 16/03/2017.
 */
public class ViewPagerTarifasAdapter extends FragmentPagerAdapter {

    private String keyTerminal;

    public ViewPagerTarifasAdapter(FragmentManager fm, String keyTerminal) {
        super(fm);
        this.keyTerminal = keyTerminal;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("keyTerminal", keyTerminal);
        Fragment fragment = null;
        switch (position)
        {
            case 0: fragment = new TarifasMovilesFragment();
                    fragment.setArguments(bundle);
                    break;

            case 1: fragment = new TarifasCombinadasFragment();
                    fragment.setArguments(bundle);
                    break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
