package com.skycaster.wuhanmappingapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public abstract class MyBaseRecyclerViewAdapter<Bean,Vh extends BaseViewHolder> extends RecyclerView.Adapter<Vh> {
    private ArrayList<Bean> mList;
    private Context mContext;

    public MyBaseRecyclerViewAdapter(ArrayList<Bean> list, Context context) {
        mList = list;
        mContext = context;
    }

    public ArrayList<Bean> getList() {
        return mList;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
