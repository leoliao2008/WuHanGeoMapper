package com.skycaster.wuhanmappingapp.fragment;

import android.support.v7.widget.RecyclerView;

import com.skycaster.wuhanmappingapp.P.LocalMapListPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.adapter.LocalMapListAdapter;
import com.skycaster.wuhanmappingapp.base.BaseFragment;
import com.tianditu.android.maps.TOfflineMapManager;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class LocalMapListFragment extends BaseFragment<LocalMapListPresenter> {
    private RecyclerView mRecyclerView;
    private LocalMapListAdapter mAdapter;
    private TOfflineMapManager mManager;


    @Override
    protected LocalMapListPresenter instantiatePresenter() {
        return null;
    }

    @Override
    protected int setRootViewId() {
        return R.layout.fragment_local_map_list;
    }

    @Override
    protected void initChildViews() {
        mRecyclerView= (RecyclerView) findViewById(R.id.fragment_local_map_list);
    }

    @Override
    protected void initData() {
        mManager = new TOfflineMapManager(
                getActivity(),
                new TOfflineMapManager.OnGetMapsResult() {
                    @Override
                    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> arrayList, int i) {

                    }
                });
        mManager.searchLocalMaps();



    }

    @Override
    protected void initListeners() {

    }
}
