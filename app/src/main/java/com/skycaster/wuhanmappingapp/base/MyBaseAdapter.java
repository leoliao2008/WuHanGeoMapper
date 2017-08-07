package com.skycaster.wuhanmappingapp.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/7.
 */

public abstract class MyBaseAdapter<T,VH extends BaseViewHolder> extends BaseAdapter {
    private ArrayList<T> mList;
    private Context mContext;
    private int mItemLayoutId;

    public MyBaseAdapter(ArrayList<T> list, Context context,int itemLayoutId) {
        mList = list;
        mContext = context;
        mItemLayoutId =itemLayoutId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if(convertView==null){
            convertView=View.inflate(mContext, mItemLayoutId,null);
            vh=instantiateViewHolder(convertView);
            convertView.setTag(vh);
        }else {
            vh= (VH) convertView.getTag();
        }
        updateViewHolder(vh,position,mList.get(position),convertView,parent);
        return convertView;
    }

    protected abstract VH instantiateViewHolder(View convertView);


    protected abstract void updateViewHolder(VH vh, int position, T item, View convertView, ViewGroup parent);

}
