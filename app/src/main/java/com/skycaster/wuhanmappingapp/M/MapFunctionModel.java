package com.skycaster.wuhanmappingapp.M;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.adapter.MapTypeSelectorAdapter;
import com.skycaster.wuhanmappingapp.base.BaseApplication;
import com.skycaster.wuhanmappingapp.bean.MapType;
import com.skycaster.wuhanmappingapp.customized.MapTypeSelector;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;

import java.util.ArrayList;

import static com.tianditu.android.maps.MapView.TMapType.MAP_TYPE_IMG;
import static com.tianditu.android.maps.MapView.TMapType.MAP_TYPE_TERRAIN;
import static com.tianditu.android.maps.MapView.TMapType.MAP_TYPE_VEC;

/**
 * Created by 廖华凯 on 2017/8/4.
 */

public class MapFunctionModel {

    private MyLocationOverlay mMyLocationOverlay;
    private PopupWindow mMapTypeWindow;

    public MapFunctionModel(MyLocationOverlay myLocationOverlay) {
        mMyLocationOverlay = myLocationOverlay;
    }

    public void initMapView(final MapView mapView){
        mapView.setBuiltInZoomControls(true);
        mapView.setDoubleTapEnable(true);
        mapView.setLogoPos(MapView.LOGO_RIGHT_BOTTOM);
        final int mapType = BaseApplication.getSharedPreferences().getInt(StaticData.MAP_TYPE, 0);
        switch (mapType){
            case 1:
                mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mapView.setMapType(MAP_TYPE_VEC);
                    }
                });
                break;
            case 2:
                mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mapView.setMapType(MAP_TYPE_IMG);
                    }
                });
                break;
            case 3:
                mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mapView.setMapType(MAP_TYPE_TERRAIN);
                    }
                });
        }
    }


    public void enableMyLocation(MapView mapView){
        mMyLocationOverlay.enableMyLocation();
        mMyLocationOverlay.setGpsFollow(true);
        mapView.getOverlays().add(mMyLocationOverlay);
    }

    public void disableMyLocation(MapView mapView){
        mMyLocationOverlay.disableMyLocation();
        mMyLocationOverlay.setGpsFollow(false);
        mapView.getOverlays().remove(mMyLocationOverlay);
    }


    public void initMapTypeSelector(final MapTypeSelector selector, final MapView mapView){
        updateCurrentMapType(selector);
        selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMapTypeWindow==null){
                    mMapTypeWindow = new PopupWindow(selector.getMeasuredWidth(),selector.getMeasuredHeight()*3);
                    ListView listView= new ListView(selector.getContext());
                    listView.setVerticalScrollBarEnabled(false);
                    listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
                    final ArrayList<MapType> mapTypes=new ArrayList<>();
                    mapTypes.add(new MapType(R.drawable.ic_map_type_vector,"矢量图",MAP_TYPE_VEC));
                    mapTypes.add(new MapType(R.drawable.ic_map_type_satellite,"卫星图",MAP_TYPE_IMG));
                    mapTypes.add(new MapType(R.drawable.ic_map_type_terrain,"地形图",MAP_TYPE_TERRAIN));
                    mapTypes.remove(selector.getMapType());
                    MapTypeSelectorAdapter adapter = new MapTypeSelectorAdapter(mapTypes, selector.getContext());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MapType temp = mapTypes.get(position);
                            if(temp.getTiandituMapTypeCode()==selector.getMapType().getTiandituMapTypeCode()){
                                mMapTypeWindow.dismiss();
                            }else {
                                selector.setMapType(temp);
                                mapView.setMapType(temp.getTiandituMapTypeCode());
                                BaseApplication.getSharedPreferences().edit().putInt(StaticData.MAP_TYPE, temp.getTiandituMapTypeCode()).apply();
                                mMapTypeWindow.dismiss();
                                mMapTypeWindow=null;
                            }
                        }
                    });
                    mMapTypeWindow.setAnimationStyle(R.style.PopWinAnimationStyle);
                    mMapTypeWindow.setContentView(listView);
                    mMapTypeWindow.setBackgroundDrawable(new ColorDrawable());
                    mMapTypeWindow.setFocusable(true);
                    mMapTypeWindow.setOutsideTouchable(true);
                    mMapTypeWindow.showAsDropDown(selector);
                }else {
                    if(mMapTypeWindow.isShowing()){
                        mMapTypeWindow.dismiss();
                    }else {
                        mMapTypeWindow.showAsDropDown(selector);
                    }

                }

            }
        });

    }

    private void updateCurrentMapType(MapTypeSelector selector){
        int tiandituMapTypeCode = BaseApplication.getSharedPreferences().getInt(StaticData.MAP_TYPE, 1);
        MapType mapType=null;
        switch (tiandituMapTypeCode){
            case 1:
                mapType=new MapType(R.drawable.ic_map_type_vector,"矢量图",MAP_TYPE_VEC);
                break;
            case 2:
                mapType=new MapType(R.drawable.ic_map_type_satellite,"卫星图",MAP_TYPE_IMG);
                break;
            case 3:
                mapType=new MapType(R.drawable.ic_map_type_terrain,"地形图",MAP_TYPE_TERRAIN);
                break;
        }
        if(mapType!=null){
            selector.setMapType(mapType);
        }
    }

    private void showLog(String msg){
        Log.e(getClass().getSimpleName(),msg);
    }

}
