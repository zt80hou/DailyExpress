package com.dlt.express.base;

import android.os.Parcelable;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by mahezhen on 17-5-22.
 * 描述：TabsPagerAdapter抽取，其他地方也能用
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fm;
    private List<String> nameList;
    private List<Fragment> fragments;
    private boolean enablePositionNone = false;//默认是 POSITION_UNCHANGED，如需要POSITION_NONE,

    public TabsPagerAdapter(FragmentManager fm, List<String> nameList, List<Fragment> fragments, boolean enablePositionNone) {
        super(fm);
        this.fm = fm;
        this.nameList = nameList;
        this.fragments = fragments;
        this.enablePositionNone = enablePositionNone;
    }

    public TabsPagerAdapter(FragmentManager fm, List<String> nameList, List<Fragment> fragments) {
        this(fm, nameList, fragments, true);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        //do nothing here! no call to super.restoreState(arg0, arg1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (nameList == null) {
            return "";
        }
        return nameList.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {// POSITION_UNCHANGED表示不会重新加载，默认是 POSITION_UNCHANGED
        if (enablePositionNone) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}