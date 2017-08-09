package com.skycaster.wuhanmappingapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public abstract class BaseFragment<P extends iPresenter> extends Fragment {
    private View rootView;
    private P mPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(setRootViewId(),null);
        return rootView;
    }

    protected abstract P instantiatePresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initChildViews();
        initData();
        instantiatePresenter();
        mPresenter.initData();
        initListeners();
    }

    protected abstract int setRootViewId();
    protected abstract void initChildViews();
    protected abstract void initData();
    protected abstract void initListeners();

    protected View findViewById(int id){
        return rootView.findViewById(id);
    }

    public P getPresenter() {
        return mPresenter;
    }

    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.onAttachedToView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachedFromView();
    }
}
