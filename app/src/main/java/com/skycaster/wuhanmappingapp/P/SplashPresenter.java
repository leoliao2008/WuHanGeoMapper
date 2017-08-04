package com.skycaster.wuhanmappingapp.P;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.skycaster.wuhanmappingapp.M.CheckPermissionModel;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.activity.SplashActivity;
import com.skycaster.wuhanmappingapp.activity.TabActivity;
import com.skycaster.wuhanmappingapp.base.BasePresenter;
import com.skycaster.wuhanmappingapp.customized.TwinklingTextView;
import com.skycaster.wuhanmappingapp.utils.AlertDialogUtil;

/**
 * Created by 廖华凯 on 2017/8/2.
 */

public class SplashPresenter extends BasePresenter<SplashActivity> {
    private CheckPermissionModel mCheckPermissionModel;

    public SplashPresenter(SplashActivity activity) {
        super(activity);
        mCheckPermissionModel=new CheckPermissionModel();
    }


    @Override
    public void initData() {
        updateVersionCode();

        if(mCheckPermissionModel.checkPermissions(getView())){
            toTabActivity();
        }else {
           requestPermissions();
        }

    }

    private void updateVersionCode(){
        try {
            PackageInfo info = getView().getPackageManager().getPackageInfo(getView().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            getView().getTv_versionCode().setText("Ver "+info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void requestPermissions(){
        mCheckPermissionModel.requestPermissions(getView());
    }

    public void toTabActivity(){
        getView().getTwinklingTextView().setAutoCycleListener(new TwinklingTextView.GradientAutoCycleListener() {
            @Override
            public void onCycleComplete() {
                TabActivity.start(getView());
                getView().finish();
            }
        });
    }

    public void setOnPermissionResultListener(final int requestCode, String[] permissions, int[] results){
        int denyIndex = mCheckPermissionModel.setOnPermissionResultListener(requestCode, results);
        if(denyIndex<0){
            toTabActivity();
        }else {
            StringBuffer sb=new StringBuffer();
            for(String s: StaticData.PERMISSIONS){
                sb.append(s).append("\r\n");
            }
            if(getView().shouldShowRequestPermissionRationale(permissions[denyIndex])){
                AlertDialogUtil.showDialog(
                        getView(),
                        "为了正常运行本程序，需要您设置以下用户权限：\r\n" + sb.toString()+"点击确认将重新申请，点击取消将退出本程序。",
                        new Runnable() {
                            @Override
                            public void run() {
                                requestPermissions();
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {
                                getView().onBackPressed();
                            }
                        }
                );
            }else {
                AlertDialogUtil.showDialog(
                        getView(),
                        "您已经禁用了以下用户权限：\r\n" + permissions[denyIndex]+"\r\n为确保程序正常运行，请先到系统设置中设置此权限。",
                        new Runnable() {
                            @Override
                            public void run() {
                                getView().onBackPressed();
                            }
                        }
                );
            }
        }
    }


}
