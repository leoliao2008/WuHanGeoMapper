package com.skycaster.wuhanmappingapp.activity;

import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import com.skycaster.wuhanmappingapp.P.OffLineMapAdminActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class OffLineMapAdminActivity extends BaseActivity<OffLineMapAdminActivityPresenter> {
    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip;

    @Override
    protected String setActionBarTitle() {
        return "离线地图管理";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_off_line_map_admin;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected iPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initListener() {

    }
}
