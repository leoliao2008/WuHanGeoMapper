package com.skycaster.wuhanmappingapp.M;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.interf.iModel;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class CheckPermissionModel implements iModel {
    private static final int REQUEST_USER_PERMISSIONS = 9527;

    public boolean checkPermissions(Activity activity){
        boolean isAllGranted=true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(String p: StaticData.PERMISSIONS){
                isAllGranted=(activity.checkSelfPermission(p)==PackageManager.PERMISSION_GRANTED);
                if(!isAllGranted){
                    break;
                }
            }
        }
        return isAllGranted;
    }

    public void requestPermissions(Activity activity){
        activity.requestPermissions(StaticData.PERMISSIONS, REQUEST_USER_PERMISSIONS);
    }

    public int setOnPermissionResultListener(int requestCode,int[] grantedResults){
        int denialIndex=-1;
        if(requestCode== REQUEST_USER_PERMISSIONS){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for(int i=0;i<grantedResults.length;i++){
                    int result = grantedResults[i];
                    if(result!=PackageManager.PERMISSION_GRANTED){
                        denialIndex=i;
                        break;
                    }
                }
            }
        }
        return denialIndex;
    }


    @Override
    public void onDetachFromPresenter() {

    }
}
