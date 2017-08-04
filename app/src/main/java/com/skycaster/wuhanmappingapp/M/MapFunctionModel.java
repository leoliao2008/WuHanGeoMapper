package com.skycaster.wuhanmappingapp.M;

import android.util.Log;
import android.view.ViewTreeObserver;

import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.base.BaseApplication;
import com.skycaster.wuhanmappingapp.customized.MapTypeToggle;
import com.tianditu.android.maps.MapView;

import static com.tianditu.android.maps.MapView.TMapType.MAP_TYPE_IMG;
import static com.tianditu.android.maps.MapView.TMapType.MAP_TYPE_VEC;

/**
 * Created by 廖华凯 on 2017/8/4.
 */

public class MapFunctionModel {


    public void initMapView(final MapView map){
        map.setBuiltInZoomControls(true);
        map.setDoubleTapEnable(true);
        map.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);
        int mapType = BaseApplication.getSharedPreferences().getInt(StaticData.MAP_TYPE, 0);
        Log.e(getClass().getSimpleName(),"Map Type= "+mapType);
        switch (mapType){
            case 0:
                map.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        map.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        map.setMapType(MAP_TYPE_VEC);
                    }
                });
                break;
            case 1:
                map.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        map.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        map.setMapType(MAP_TYPE_IMG);
                    }
                });
                break;
        }
    }

    public void toggleMapType(MapView map, MapTypeToggle.MapType mapType){
        switch (mapType){
            case MAP_TYPE_SATELLITE:
                map.setMapType(MAP_TYPE_IMG);
                break;
            case MAP_TYPE_VECTOR:
                map.setMapType(MAP_TYPE_VEC);
                break;
        }
    }

}
