package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.skycaster.wuhanmappingapp.P.MapAdminActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseMvpActivity;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class MapAdminActivity extends BaseMvpActivity<MapAdminActivityPresenter> {
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;

    public static void start(Context context) {
        Intent starter = new Intent(context, MapAdminActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected String setActionBarTitle() {
        return "离线地图管理";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_map_admin;
    }

    @Override
    protected void initView() {
        mViewPager= (ViewPager) findViewById(R.id.offline_map_admin_view_pager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.offline_map_admin_pager_tab_strip);
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public PagerTabStrip getPagerTabStrip() {
        return mPagerTabStrip;
    }

    @Override
    protected iPresenter initPresenter() {
        return new MapAdminActivityPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
