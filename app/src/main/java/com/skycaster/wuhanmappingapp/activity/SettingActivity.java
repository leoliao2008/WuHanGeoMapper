package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.P.SettingPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SettingActivity extends BaseActivity<SettingPresenter> {
    private TextView tv_serialPort;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected String setActionBarTitle() {
        return "系统设置";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        tv_serialPort= (TextView) findViewById(R.id.setting_serial_port);

    }

    public TextView getTv_serialPort() {
        return tv_serialPort;
    }

    @Override
    protected iPresenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
