package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;

import com.skycaster.wuhanmappingapp.P.MapActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.customized.MapTypeSelector;
import com.skycaster.wuhanmappingapp.interf.iPresenter;
import com.tianditu.android.maps.MapView;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapActivity extends BaseActivity<MapActivityPresenter>{
    private MapView mMapView;
    private MapTypeSelector mMapTypeSelector;

    public static void start(Context context) {
        Intent starter = new Intent(context, MapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected String setActionBarTitle() {
        return "地图界面";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {
        mMapView= (MapView) findViewById(R.id.map_activity_map_view);
        mMapTypeSelector = (MapTypeSelector) findViewById(R.id.map_activity_map_selector);
    }

    public MapView getMapView() {
        return mMapView;
    }

    public MapTypeSelector getMapTypeSelector() {
        return mMapTypeSelector;
    }

    @Override
    protected iPresenter initPresenter() {
        return new MapActivityPresenter(this);
    }

    @Override
    protected void initListener() {

    }

}
