package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;

import com.skycaster.wuhanmappingapp.P.MapPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.customized.MapTypeSelector;
import com.skycaster.wuhanmappingapp.interf.iPresenter;
import com.tianditu.android.maps.MapView;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapActivity extends BaseActivity<MapPresenter>{
    private MapView mMapView;
//    private MapTypeToggle mMapTypeSelector;
//    private Spinner mMapTypeSelector;
    private MapTypeSelector mMapTypeSelector;

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
//        mMapTypeSelector= (MapTypeToggle) findViewById(R.id.map_activity_map_type_toggle);
//        mMapTypeSelector = (Spinner) findViewById(R.id.map_activity_spinner_map_type);
        mMapTypeSelector = (MapTypeSelector) findViewById(R.id.map_activity_map_selector);


    }

    public MapView getMapView() {
        return mMapView;
    }

//    public MapTypeToggle getMapTypeSelector() {
//        return mMapTypeSelector;
//    }


//    public Spinner getMapTypeSelector() {
//        return mMapTypeSelector;
//    }


    public MapTypeSelector getMapTypeSelector() {
        return mMapTypeSelector;
    }

    @Override
    protected iPresenter initPresenter() {
        return new MapPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
