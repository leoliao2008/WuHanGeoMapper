package com.skycaster.wuhanmappingapp.P;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;

import com.skycaster.inertial_navi_lib.GPGGABean;
import com.skycaster.inertial_navi_lib.NaviDataExtractor;
import com.skycaster.wuhanmappingapp.M.MapFunctionModel;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.activity.MapActivity;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;
import com.skycaster.wuhanmappingapp.customized.SkyCasterPositioningOverlay;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class MapActivityPresenter extends BaseActivityPresenter<MapActivity> {
    private MapFunctionModel mMapFunctionModel;
    private MapView mMapView;
    private SkyCasterPositioningOverlay mPositioningOverlay;
    private GPGGALocationUpdateReceiver mReceiver;
    private NaviDataExtractor.CallBack mCallBack=new NaviDataExtractor.CallBack() {
        @Override
        public void onGetGPGGABean(GPGGABean bean) {
            if(mPositioningOverlay!=null){
                mPositioningOverlay.onPositionUpdate(bean,mMapView);
            }
        }
    };

    public MapActivityPresenter(MapActivity activity) {
        super(activity);
        mMapFunctionModel=new MapFunctionModel(new MyLocationOverlay(getView(),getView().getMapView()));
        mMapView=getView().getMapView();
    }

    @Override
    public void initData() {
        ActionBar actionBar = getView().getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("地图页面");
        }
        mMapFunctionModel.initMapView(mMapView);

        mMapFunctionModel.initMapTypeSelector(getView().getMapTypeSelector(),mMapView);

//        mMapFunctionModel.enableMyLocation(mMapView); 暂不需要
        mPositioningOverlay = new SkyCasterPositioningOverlay(getView());
        mMapView.addOverlay(mPositioningOverlay);
    }

    @Override
    public void onAttachedToView() {
        initReceiver();
    }

    private void initReceiver(){
        mReceiver=new GPGGALocationUpdateReceiver();
        IntentFilter intentFilter=new IntentFilter(StaticData.ACTION_RECEIVE_BROADCASTING_GPGGA_RAW_DATA);
        LocalBroadcastManager.getInstance(getView()).registerReceiver(mReceiver,intentFilter);
    }

    @Override
    public void onDetachedFromView() {
        if(mReceiver!=null){
            LocalBroadcastManager.getInstance(getView()).unregisterReceiver(mReceiver);
            NaviDataExtractor.stopExtractingGPGGAData();
        }
    }

    private class GPGGALocationUpdateReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] data = intent.getByteArrayExtra(StaticData.PORT_DATA_RAW);
            NaviDataExtractor.decipherData(data, data.length, mCallBack);
        }
    }
}
