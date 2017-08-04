package com.skycaster.wuhanmappingapp.P;

import com.skycaster.wuhanmappingapp.M.MapFunctionModel;
import com.skycaster.wuhanmappingapp.activity.MapActivity;
import com.skycaster.wuhanmappingapp.base.BasePresenter;
import com.skycaster.wuhanmappingapp.customized.MapTypeToggle;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapPresenter extends BasePresenter<MapActivity> {
    private MapFunctionModel mMapFunctionModel;


    public MapPresenter(MapActivity activity) {
        super(activity);
        mMapFunctionModel=new MapFunctionModel();
    }

    @Override
    public void initData() {
        mMapFunctionModel.initMapView(getView().getMapView());


        getView().getMapTypeToggle().setMapTypeChangeListener(new MapTypeToggle.MapTypeChangeListener() {
            @Override
            public void onMapTypeChange(MapTypeToggle.MapType mapType) {
               mMapFunctionModel.toggleMapType(getView().getMapView(),mapType);
            }
        });




    }
}
