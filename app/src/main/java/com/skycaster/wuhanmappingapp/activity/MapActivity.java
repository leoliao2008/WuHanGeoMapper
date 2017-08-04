package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;

import com.skycaster.wuhanmappingapp.P.MapPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.customized.MapTypeToggle;
import com.skycaster.wuhanmappingapp.interf.iPresenter;
import com.tianditu.android.maps.MapView;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapActivity extends BaseActivity<MapPresenter>{
    private MapView mMapView;
    private MapTypeToggle mMapTypeToggle;

    public static void start(Context context) {
        Intent starter = new Intent(context, MapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {
        mMapView= (MapView) findViewById(R.id.map_activity_map_view);
        mMapTypeToggle= (MapTypeToggle) findViewById(R.id.map_activity_map_type_toggle);

    }

    public MapView getMapView() {
        return mMapView;
    }

    public MapTypeToggle getMapTypeToggle() {
        return mMapTypeToggle;
    }

    @Override
    protected iPresenter initPresenter() {
        return new MapPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
