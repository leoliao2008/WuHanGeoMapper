package com.skycaster.wuhanmappingapp.P;

import android.view.View;

import com.skycaster.wuhanmappingapp.activity.MapAdminActivity;
import com.skycaster.wuhanmappingapp.activity.SerialPortActivity;
import com.skycaster.wuhanmappingapp.activity.SettingActivity;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SettingActivityPresenter extends BaseActivityPresenter<SettingActivity> {

    public SettingActivityPresenter(SettingActivity view) {
        super(view);
    }

    @Override
    public void initData() {
        getView().getTv_serialPort().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SerialPortActivity.start(getView());
            }
        });

        getView().getTv_mapAdmin().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapAdminActivity.start(getView());
            }
        });

        getView().getTv_aboutUs().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
