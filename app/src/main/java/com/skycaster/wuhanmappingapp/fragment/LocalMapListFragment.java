package com.skycaster.wuhanmappingapp.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.skycaster.wuhanmappingapp.P.LocalMapListPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseFragment;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapListFragment extends BaseFragment<LocalMapListPresenter> {

    private RecyclerView mRecyclerView;
    private ImageView tv_noData;


    @Override
    protected LocalMapListPresenter instantiatePresenter() {
        return new LocalMapListPresenter(this);
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_local_map_list;
    }

    @Override
    protected void initChildViews() {
        mRecyclerView= (RecyclerView) findViewById(R.id.fragment_local_map_list);
        tv_noData= (ImageView) findViewById(R.id.fragment_local_map_list_iv_no_data);
    }

    @Override
    protected void initData() {

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    public ImageView getTv_noData() {
        return tv_noData;
    }

    @Override
    protected void initListeners() {

    }
}
