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
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    String SP_NAME="CONFIG";
    String MAP_TYPE="MAP_TYPE";
    String SERIAL_PORT_PATH="SERIAL_PORT_PATH";
    String BAUD_RATE="BAUD_RATE";
    String ACTION_RECEIVE_BROADCASTING_GPGGA_RAW_DATA ="ACTION_RECEIVE_BROADCASTING_GPGGA_RAW_DATA";
    String ACTION_STOP_GPGGA_SERVICE="ACTION_STOP_GPGGA_SERVICE";
    int GPGGA_SERVICE_FOREGROUND_ID =1122;
    String PORT_DATA_RAW ="PORT_DATA_RAW";
    int GPGGA_DATA_BUFFER_SIZE=512;
    String ACTION_RECEIVE_BROADCASTING_MAP_DOWN_LOAD_PROGRESS="ACTION_RECEIVE_BROADCASTING_MAP_DOWN_LOAD_PROGRESS";
    String MAP_STATUS="MAP_STATUS";
    String MAP_DOWN_LOADED_SIZE="MAP_DOWN_LOADED_SIZE";
    String MAP_NAME = "MAP_NAME";
}
