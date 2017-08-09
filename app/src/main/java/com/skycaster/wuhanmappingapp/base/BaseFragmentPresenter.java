package com.skycaster.wuhanmappingapp.base;

import android.support.v4.app.Fragment;

import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public abstract class BaseFragmentPresenter<F extends Fragment> implements iPresenter {
    private F mView;

    public BaseFragmentPresenter(F view) {
        mView = view;
    }

    public F getView() {
        return mView;
    }
}
