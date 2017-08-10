package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.skycaster.wuhanmappingapp.base.BaseMvpActivity;
import com.skycaster.wuhanmappingapp.P.NavigationActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class NavigationActivity extends BaseMvpActivity<NavigationActivityPresenter> {
    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, NavigationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected String setActionBarTitle() {
        return "功能菜单";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void initView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.navigation_recycler_view);

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    protected iPresenter initPresenter() {
        return new NavigationActivityPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
