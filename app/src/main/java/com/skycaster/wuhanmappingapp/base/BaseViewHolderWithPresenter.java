package com.skycaster.wuhanmappingapp.base;

import android.view.View;

import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public abstract class BaseViewHolderWithPresenter<P extends iPresenter> extends BaseViewHolder  {
    private P mPresenter;

    public BaseViewHolderWithPresenter(View itemView,P presenter) {
        super(itemView);
        mPresenter=presenter;
    }

    public P getPresenter() {
        return mPresenter;
    }
    public void onAttachedToWindow(){
        mPresenter.onAttachedToView();
    }
    public void onDetachedFromWindow(){
        mPresenter.onDetachedFromView();
    }
}
