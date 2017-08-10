package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.P.SettingActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseMvpActivity;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SettingActivity extends BaseMvpActivity<SettingActivityPresenter> {
    private TextView tv_serialPort;
    private TextView tv_mapAdmin;
    private TextView tv_aboutUs;

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
        tv_mapAdmin= (TextView) findViewById(R.id.setting_map_admin);
        tv_aboutUs= (TextView) findViewById(R.id.setting_about_us);

    }

    public TextView getTv_serialPort() {
        return tv_serialPort;
    }

    public TextView getTv_mapAdmin() {
        return tv_mapAdmin;
    }

    public TextView getTv_aboutUs() {
        return tv_aboutUs;
    }

    @Override
    protected iPresenter initPresenter() {
        return new SettingActivityPresenter(this);
    }

    @Override
    protected void initListener() {

    }
}
