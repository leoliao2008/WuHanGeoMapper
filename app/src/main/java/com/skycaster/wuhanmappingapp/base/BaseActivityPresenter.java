package com.skycaster.wuhanmappingapp.base;

import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public abstract class BaseActivityPresenter<V extends BaseMvpActivity> implements iPresenter {
    private V mView;

    public BaseActivityPresenter(V view) {
        mView = view;
    }

    protected V getView(){
        return mView;
    }

    @Override
    public void onDetachedFromView() {

    }

    @Override
    public void onAttachedToView() {

    }
}
