package com.skycaster.wuhanmappingapp.P;

import android.view.View;

import com.skycaster.wuhanmappingapp.activity.SerialPortActivity;
import com.skycaster.wuhanmappingapp.activity.SettingActivity;
import com.skycaster.wuhanmappingapp.base.BasePresenter;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SettingPresenter extends BasePresenter<SettingActivity> {

    public SettingPresenter(SettingActivity view) {
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

    }

    @Override
    public void onDetachFromView() {

    }
}
