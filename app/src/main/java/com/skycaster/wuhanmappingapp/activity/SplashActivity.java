package com.skycaster.wuhanmappingapp.activity;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.P.SplashActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseActivity;
import com.skycaster.wuhanmappingapp.customized.TwinklingTextView;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

public class SplashActivity extends BaseActivity<SplashActivityPresenter> {
    private TextView tv_versionCode;
    private TwinklingTextView mTwinklingTextView;


    @Override
    protected iPresenter initPresenter() {
        return new SplashActivityPresenter(this);
    }

    @Override
    protected String setActionBarTitle() {
        return "欢迎界面";
    }

    @Override
    public int setContentViewID() {
        return R.layout.activity_splash;
    }


    @Override
    public void initView() {
        tv_versionCode= (TextView) findViewById(R.id.splash_tv_ver_code);
        mTwinklingTextView= (TwinklingTextView) findViewById(R.id.splash_twinkling_text_view);
    }

    public TextView getTv_versionCode() {
        return tv_versionCode;
    }

    public TwinklingTextView getTwinklingTextView() {
        return mTwinklingTextView;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        getPresenter().setOnPermissionResultListener(requestCode,permissions,grantResults);
    }
}
