package com.skycaster.wuhanmappingapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        initChildViews();
    }

    public Context getContext(){
        return itemView.getContext();
    }

    protected abstract void initChildViews();

    protected View findViewById(int id){
        return itemView.findViewById(id);
    }
}
