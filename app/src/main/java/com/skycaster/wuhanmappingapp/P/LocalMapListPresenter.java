package com.skycaster.wuhanmappingapp.P;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.skycaster.wuhanmappingapp.M.MapAdminModel;
import com.skycaster.wuhanmappingapp.adapter.LocalMapListAdapter;
import com.skycaster.wuhanmappingapp.base.BaseFragmentPresenter;
import com.skycaster.wuhanmappingapp.fragment.LocalMapListFragment;
import com.skycaster.wuhanmappingapp.utils.AlertDialogUtil;
import com.skycaster.wuhanmappingapp.view_holders.LocalMapItemViewHolder;
import com.tianditu.android.maps.TOfflineMapInfo;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapListPresenter extends BaseFragmentPresenter<LocalMapListFragment> {
    private LocalMapListAdapter mAdapter;
    private ArrayList<TOfflineMapInfo> mDownLoadedList=new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private MapAdminModel mMapAdminModel;


    public LocalMapListPresenter(LocalMapListFragment view) {
        super(view);
        mMapAdminModel=new MapAdminModel(getView().getActivity());
    }

    @Override
    public void initData() {

        mAdapter=new LocalMapListAdapter(
                mDownLoadedList,
                getView().getActivity(),
                new LocalMapListAdapter.CallBack() {
                    @Override
                    public void onResume(LocalMapItemViewHolder holder, int position, TOfflineMapInfo info) {
                        mMapAdminModel.startDownLoad(info.getCity(),info.getType());
                    }

                    @Override
                    public void onUpgrade(LocalMapItemViewHolder holder, int position, TOfflineMapInfo info) {
                        if(mMapAdminModel.checkIfUpdateAvailable(info.getCity(),info.getType(),info.getVersion())){
                            mMapAdminModel.deleteLocalMap(info.getCity(),info.getType());
                            mDownLoadedList.remove(position);
                            mDownLoadedList.add(position,mMapAdminModel.getMapInfo(info.getCity(),info.getType()));
                            mAdapter.notifyDataSetChanged();
                            mMapAdminModel.startDownLoad(info.getCity(),info.getType());
                        }

                    }

                    @Override
                    public void onDelete(LocalMapItemViewHolder holder, int position, final TOfflineMapInfo info) {
                        AlertDialogUtil.showDialog(
                                getView().getActivity(),
                                "您确定要删除该地图吗？",
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        removeMap(info);
                                    }
                                });
                    }

                    @Override
                    public void onPause(LocalMapItemViewHolder holder, int position, TOfflineMapInfo info) {
                        mMapAdminModel.pauseDownLoad(info.getCity(),info.getType());
                    }
                }
        );

        mLayoutManager=new LinearLayoutManager(getView().getActivity(),LinearLayoutManager.VERTICAL,false);
        getView().getRecyclerView().setLayoutManager(mLayoutManager);
        getView().getRecyclerView().setAdapter(mAdapter);
        reloadMapList();

    }



    public void reloadMapList(){
        mDownLoadedList.clear();
        mDownLoadedList.addAll(mMapAdminModel.getLocalMaps());
        mAdapter.notifyDataSetChanged();
        if(mDownLoadedList.size()>0){
            getView().getTv_noData().setVisibility(View.GONE);
        }else {
            getView().getTv_noData().setVisibility(View.VISIBLE);
        }
    }

    public void addMap(TOfflineMapInfo info){
        mDownLoadedList.add(info);
        mAdapter.notifyDataSetChanged();
        getView().getTv_noData().setVisibility(View.GONE);
        getView().getRecyclerView().smoothScrollToPosition(Integer.MAX_VALUE);
    }

    public void removeMap(TOfflineMapInfo info){
        mMapAdminModel.deleteLocalMap(info.getCity(),info.getType());
        if(mDownLoadedList.remove(info)){
            mAdapter.notifyDataSetChanged();
        }
        if(mDownLoadedList.size()>0){
            getView().getTv_noData().setVisibility(View.GONE);
        }else {
            getView().getTv_noData().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttachedToView() {

    }

    @Override
    public void onDetachedFromView() {

    }
}
