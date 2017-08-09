package com.skycaster.wuhanmappingapp.M;

import android.os.Environment;

/**
 * Created by 廖华凯 on 2017/8/9.
 */

public class ExternalStorageModel {

    public boolean checkIfSdCardAvailable(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public long getFreeSpace(){
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }
}
