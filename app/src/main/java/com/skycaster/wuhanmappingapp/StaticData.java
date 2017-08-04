package com.skycaster.wuhanmappingapp;

import android.Manifest;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public interface StaticData {
//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE">
//    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE">
//    <uses-permission android:name="android.permission.INTERNET">
//    <uses-permission android:name="android.permission.CALL_PHONE">
//    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION">
//    <uses-permission android:name="android.permission.READ_PHONE_STATE">
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE">

    String[] PERMISSIONS =new String[]{
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String SP_NAME="CONFIG";
    String MAP_TYPE="MAP_TYPE";
}
