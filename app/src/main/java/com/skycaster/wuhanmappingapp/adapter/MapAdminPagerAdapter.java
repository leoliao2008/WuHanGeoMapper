package com.skycaster.wuhanmappingapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skycaster.wuhanmappingapp.fragment.LocalMapListFragment;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/10.
 */

public class MapAdminPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list=new ArrayList<>();
    private String[] titles=new String[]{"本地离线地图","离线地图下载"};
    public MapAdminPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new LocalMapListFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
