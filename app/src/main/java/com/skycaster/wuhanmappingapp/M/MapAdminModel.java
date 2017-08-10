package com.skycaster.wuhanmappingapp.M;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.interf.iModel;
import com.tianditu.android.maps.TOfflineMapInfo;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class MapAdminModel implements iModel {
    private TOfflineMapManager manager;
    private Context mContext;
    private ArrayList<TOfflineMapInfo> mLocalMaps=new ArrayList<>();

    public MapAdminModel(Context context) {
        mContext=context;
        manager =new TOfflineMapManager(context, new TOfflineMapManager.OnGetMapsResult() {
            @Override
            public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> arrayList, int i) {

            }
        });
        manager.setMapPath(StaticData.LOCAL_MAP_PATH);
        manager.setOnDownLoadListener(new TOfflineMapManager.OnDownLoadResult() {
            @Override
            public void onDownLoadStart(String s, int i, int i1) {
                broadcastDownloadInfo(s,i,TOfflineMapManager.OFFLINEMAP_DOWNLOADING);
            }

            @Override
            public void onDownLoadStop(String s, int i, int i1) {
                broadcastDownloadInfo(s,i,TOfflineMapManager.OFFLINEMAP_DOWNLOAD_PAUSE);
            }

            @Override
            public void onDownLoadData(String s, int i, int i1) {
                broadcastDownloadInfo(s,i,TOfflineMapManager.OFFLINEMAP_DOWNLOADING);
            }

            @Override
            public void onDownLoadOver(String s, int i, int i1) {
                broadcastDownloadInfo(s,i,TOfflineMapManager.OFFLINEMAP_DOWNLOAD_FINISHED);
            }

            @Override
            public void onDownLoadDelete(String s, int i, int i1) {

            }
        });
    }

    public ArrayList<TOfflineMapInfo> getLocalMaps(){
        mLocalMaps.clear();
        ArrayList<TOfflineMapInfo> localMaps = manager.searchLocalMaps();
        if(localMaps!=null){
            mLocalMaps.addAll(localMaps);
        }
        ArrayList<TOfflineMapInfo> pausedMaps = manager.getPausedMaps();
        if(pausedMaps!=null){
            mLocalMaps.addAll(pausedMaps);
        }
        ArrayList<TOfflineMapInfo> downLoadingMaps = manager.getDownLoadingMaps();
        if(downLoadingMaps!=null){
            mLocalMaps.addAll(downLoadingMaps);
        }
        return mLocalMaps;
    }

    public void startDownLoad(String cityName,int mapType){
        manager.startDownload(cityName,mapType);
    }

    public void pauseDownLoad(String cityName,int mapType){
        manager.pauseDownload(cityName,mapType);
    }

    public void deleteLocalMap(String cityName, int mapType){
        manager.deleteMap(cityName,mapType);
    }

    public TOfflineMapInfo getMapInfo(String cityName,int mapType){
        return manager.getDownloadInfo(cityName,mapType);
    }

    public boolean checkIfUpdateAvailable(String cityName,int mapType,int localVersion){
        ArrayList<TOfflineMapInfo> updateMaps = manager.getAllUpdateMaps();
        if(updateMaps!=null&&updateMaps.size()>0){
            for(TOfflineMapInfo temp:updateMaps){
                if(temp.getCity().equals(cityName)&&temp.getType()==mapType&&temp.getVersion()>localVersion){
                    return true;
                }
            }
        }
        return false;
    }

    private void broadcastDownloadInfo(String cityName, int mapType, int statusCode) {
        Intent intent=new Intent(StaticData.ACTION_RECEIVE_BROADCASTING_MAP_DOWN_LOAD_PROGRESS);
        TOfflineMapInfo info = manager.getDownloadInfo(cityName, mapType);
        intent.putExtra(StaticData.MAP_TYPE,mapType);
        intent.putExtra(StaticData.MAP_NAME,cityName);
        intent.putExtra(StaticData.MAP_DOWN_LOADED_SIZE,info.getDownloadedSize());
        intent.putExtra(StaticData.MAP_STATUS,statusCode);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }


    @Override
    public void onDetachFromPresenter() {

    }
}
