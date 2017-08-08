package com.skycaster.wuhanmappingapp.base;

import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public abstract class BasePresenter<V extends BaseActivity> implements iPresenter {
    private V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    protected V getView(){
        return mView;
    }


}
