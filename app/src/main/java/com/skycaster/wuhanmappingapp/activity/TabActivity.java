package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.P.TabPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class TabActivity extends BaseActivity<TabPresenter> {
    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, TabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.tab_recycler_view);

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    protected iPresenter initPresenter() {
        return new TabPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
