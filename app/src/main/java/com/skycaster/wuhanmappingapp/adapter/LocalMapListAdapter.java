package com.skycaster.wuhanmappingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.skycaster.wuhanmappingapp.P.LocalMapListItemPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.MyBaseRecyclerViewAdapter;
import com.skycaster.wuhanmappingapp.view_holders.LocalMapItemViewHolder;
import com.tianditu.android.maps.TOfflineMapInfo;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapListAdapter extends MyBaseRecyclerViewAdapter<TOfflineMapInfo,LocalMapItemViewHolder> {
    private CallBack mCallBack;

    public LocalMapListAdapter(ArrayList<TOfflineMapInfo> list, Context context,LocalMapListAdapter.CallBack callBack) {
        super(list, context);
        mCallBack=callBack;
    }

    @Override
    public LocalMapItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocalMapItemViewHolder(View.inflate(getContext(), R.layout.item_local_map,null), new LocalMapListItemPresenter());
    }

    @Override
    public void onBindViewHolder(final LocalMapItemViewHolder holder, final int position) {
        final TOfflineMapInfo info = getList().get(position);
        holder.getPresenter().populateItem(holder, info, new LocalMapListItemPresenter.CallBack() {
            @Override
            public void onUpgrade() {
                mCallBack.onUpgrade(holder,position,info);
            }

            @Override
            public void onResume() {
                mCallBack.onResume(holder,position,info);
            }

            @Override
            public void onPause() {
                mCallBack.onPause(holder,position,info);
            }

            @Override
            public void onDelete() {
                mCallBack.onDelete(holder,position,info);
            }
        });

    }

    @Override
    public void onViewAttachedToWindow(LocalMapItemViewHolder holder) {
        holder.onAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(LocalMapItemViewHolder holder) {
        holder.onDetachedFromWindow();
    }

    public interface CallBack{
        void onResume(LocalMapItemViewHolder holder, int position,TOfflineMapInfo info);
        void onUpgrade(LocalMapItemViewHolder holder, int position,TOfflineMapInfo info);
        void onDelete(LocalMapItemViewHolder holder, int position,TOfflineMapInfo info);
        void onPause(LocalMapItemViewHolder holder, int position,TOfflineMapInfo info);
    }
}
