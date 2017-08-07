package com.skycaster.wuhanmappingapp.P;

import com.skycaster.wuhanmappingapp.M.MapFunctionModel;
import com.skycaster.wuhanmappingapp.activity.MapActivity;
import com.skycaster.wuhanmappingapp.base.BasePresenter;
import com.tianditu.android.maps.MyLocationOverlay;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapPresenter extends BasePresenter<MapActivity> {
    private MapFunctionModel mMapFunctionModel;



    public MapPresenter(MapActivity activity) {
        super(activity);
        mMapFunctionModel=new MapFunctionModel(new MyLocationOverlay(getView(),getView().getMapView()));
    }

    @Override
    public void initData() {
        mMapFunctionModel.initMapView(getView().getMapView());

        mMapFunctionModel.initMapTypeSelector(getView().getMapTypeSelector(),getView().getMapView());

        mMapFunctionModel.enableMyLocation(getView().getMapView());


    }
}
