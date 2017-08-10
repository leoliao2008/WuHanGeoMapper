package com.skycaster.wuhanmappingapp.P;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.activity.MapActivity;
import com.skycaster.wuhanmappingapp.activity.SettingActivity;
import com.skycaster.wuhanmappingapp.activity.NavigationActivity;
import com.skycaster.wuhanmappingapp.adapter.NavigationGridAdapter;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;
import com.skycaster.wuhanmappingapp.bean.NavigationItem;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class NavigationActivityPresenter extends BaseActivityPresenter<NavigationActivity> {
    private RecyclerView mRecyclerView;
    private NavigationGridAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NavigationItem> mList;

    public NavigationActivityPresenter(NavigationActivity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mRecyclerView=getView().getRecyclerView();
        mLayoutManager = new GridLayoutManager(getView(),3, LinearLayoutManager.VERTICAL,false);
        mList=new ArrayList<>();
        mList.add(new NavigationItem("地图页面", R.drawable.ic_navi));
        mList.add(new NavigationItem("历史记录",R.drawable.ic_history));
        mList.add(new NavigationItem("系统设置",R.drawable.ic_setting));
        mAdapter=new NavigationGridAdapter(mList,getView());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NavigationGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NavigationItem item, int position) {
                switch (position){
                    case 0:
                        MapActivity.start(getView());
                        break;
                    case 1:
                        break;
                    case 2:
                        SettingActivity.start(getView());
                        break;
                }
            }
        });
    }

}
