package com.skycaster.wuhanmappingapp.P;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;

import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.interf.iPresenter;
import com.skycaster.wuhanmappingapp.view_holders.LocalMapItemViewHolder;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TOfflineMapInfo;
import com.tianditu.android.maps.TOfflineMapManager;


/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapItemPresenter implements iPresenter {
    private LocalMapItemViewHolder mView;
    private TOfflineMapInfo mInfo;
    private CallBack mCallBack;
    private MapDownLoadUpdateReceiver mReceiver;



    public void populateItem(LocalMapItemViewHolder view, TOfflineMapInfo info,CallBack callBack){
        mView = view;
        mInfo=info;
        mCallBack=callBack;
        initData();
    }

    @Override
    public void initData() {
        mView.getTv_city().setText(mInfo.getCity());
        mView.getTv_totalSize().setText("地图大小："+Formatter.formatFileSize(mView.getContext(),mInfo.getSize()));
        mView.getTv_version().setText("版本："+mInfo.getVersion());
        switch (mInfo.getType()){
            case MapView.TMapType.MAP_TYPE_IMG:
                mView.getTv_type().setText("类型：卫星图");
                break;
            case MapView.TMapType.MAP_TYPE_VEC:
                mView.getTv_type().setText("类型：矢量图");
                break;
            default:
                mView.getTv_type().setText("类型：未知");
                break;
        }
        updateState(mInfo.getState());
        updateProgressbar(mInfo.getDownloadedSize());
        mView.getBtn_pause().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onPause();
            }
        });
        mView.getBtn_delete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onDelete();
            }
        });
        mView.getBtn_upgrade().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onUpgrade();
            }
        });
        mView.getBtn_resume().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onResume();
            }
        });
    }



    private void updateProgressbar(int downloadedSize) {
        mView.getTv_currentSize().setText("已下载："+Formatter.formatFileSize(mView.getContext(),downloadedSize));
        double i = downloadedSize / mInfo.getSize();
        mView.getTv_progress().setText(String.format("#.0f", i)+"%");
        mView.getProgressBar().setProgress((int) (100*i));
    }

    private void updateState(int state) {
        resetButtonStates();
        String description;
        switch (state){
            case TOfflineMapManager.OFFLINEMAP_DOWNLOAD_FINISHED:
                description="状态：已下载完成";
                //留下升级、删除
                mView.getBtn_resume().setVisibility(View.GONE);
                mView.getBtn_pause().setVisibility(View.GONE);
                break;
            case TOfflineMapManager.OFFLINEMAP_DOWNLOAD_PAUSE:
                description="状态：已暂停";
                //留下继续、删除
                mView.getBtn_pause().setVisibility(View.GONE);
                mView.getBtn_upgrade().setVisibility(View.GONE);
                break;
            case TOfflineMapManager.OFFLINEMAP_DOWNLOADING:
                description="状态：下载中";
                //留下暂停、删除
                mView.getBtn_upgrade().setVisibility(View.GONE);
                mView.getBtn_resume().setVisibility(View.GONE);
                break;
            case TOfflineMapManager.OFFLINEMAP_DOWNLOAD_UNDEFINE:
            default:
                description="状态：未知状态";
                //留下删除
                mView.getBtn_resume().setVisibility(View.GONE);
                mView.getBtn_upgrade().setVisibility(View.GONE);
                mView.getBtn_pause().setVisibility(View.GONE);
                break;
        }
        mView.getTv_status().setText(description);
    }

    private void resetButtonStates(){
        mView.getBtn_delete().setVisibility(View.VISIBLE);
        mView.getBtn_pause().setVisibility(View.VISIBLE);
        mView.getBtn_delete().setVisibility(View.VISIBLE);
        mView.getBtn_resume().setVisibility(View.VISIBLE);
    }

    private void registerReceiver() {
        mReceiver=new MapDownLoadUpdateReceiver();
        IntentFilter intentFilter=new IntentFilter(StaticData.ACTION_RECEIVE_BROADCASTING_MAP_DOWN_LOAD_PROGRESS);
        LocalBroadcastManager.getInstance(mView.getContext()).registerReceiver(mReceiver,intentFilter);
    }

    @Override
    public void onAttachedToView() {
        registerReceiver();
    }

    @Override
    public void onDetachedFromView() {
        if(mReceiver!=null){
            LocalBroadcastManager.getInstance(mView.getContext()).unregisterReceiver(mReceiver);
        }
    }

    public interface CallBack{
        void onUpgrade();
        void onResume();
        void onPause();
        void onDelete();
    }

    private void updateItemView(int status, int loadedSize) {
        updateState(status);
        updateProgressbar(loadedSize);
    }

    public class MapDownLoadUpdateReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(StaticData.MAP_STATUS, TOfflineMapManager.OFFLINEMAP_DOWNLOAD_UNDEFINE);
            int loadedSize = intent.getIntExtra(StaticData.MAP_DOWN_LOADED_SIZE, 0);
            String cityName=intent.getStringExtra(StaticData.MAP_NAME);
            if(!TextUtils.isEmpty(cityName)&&cityName.equals(mInfo.getCity())){
                updateItemView(status,loadedSize);
            }
        }
    }
}
