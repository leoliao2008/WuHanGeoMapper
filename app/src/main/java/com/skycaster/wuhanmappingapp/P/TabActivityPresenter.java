package com.skycaster.wuhanmappingapp.P;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.activity.MapActivity;
import com.skycaster.wuhanmappingapp.activity.SettingActivity;
import com.skycaster.wuhanmappingapp.activity.TabActivity;
import com.skycaster.wuhanmappingapp.adapter.TabAdapter;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;
import com.skycaster.wuhanmappingapp.bean.TabItem;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class TabActivityPresenter extends BaseActivityPresenter<TabActivity> {
    private RecyclerView mRecyclerView;
    private TabAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<TabItem> mList;

    public TabActivityPresenter(TabActivity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mRecyclerView=getView().getRecyclerView();
        mLayoutManager = new GridLayoutManager(getView(),3, LinearLayoutManager.VERTICAL,false);
        mList=new ArrayList<>();
        mList.add(new TabItem("地图页面", R.drawable.ic_navi));
        mList.add(new TabItem("历史记录",R.drawable.ic_history));
        mList.add(new TabItem("系统设置",R.drawable.ic_setting));
        mAdapter=new TabAdapter(mList,getView());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new TabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TabItem item, int position) {
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
