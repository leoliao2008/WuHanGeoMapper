package com.skycaster.wuhanmappingapp.P;

import android.support.v4.view.PagerTabStrip;
import android.util.TypedValue;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.activity.MapAdminActivity;
import com.skycaster.wuhanmappingapp.adapter.MapAdminPagerAdapter;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class MapAdminPresenter extends BaseActivityPresenter<MapAdminActivity> {
    private MapAdminPagerAdapter mAdapter;
    public MapAdminPresenter(MapAdminActivity view) {
        super(view);
    }

    @Override
    public void initData() {
        mAdapter=new MapAdminPagerAdapter(getView().getSupportFragmentManager());
        getView().getViewPager().setAdapter(mAdapter);
        initPagerTabStrip(getView().getPagerTabStrip());


    }

    private void initPagerTabStrip(PagerTabStrip pagerTabStrip) {
        int padding=getView().getResources().getDimensionPixelSize(R.dimen.dimen_padding_10dp);
        pagerTabStrip.setPadding(padding,padding,padding,padding);
        pagerTabStrip.setTabIndicatorColorResource(R.color.colorPrimary);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setBackgroundResource(R.color.colorBg);
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_PX,getView().getResources().getDimension(R.dimen.dimen_text_size_18sp));
    }


    @Override
    public void onAttachedToView() {

    }

    @Override
    public void onDetachedFromView() {

    }
}
