package com.skycaster.wuhanmappingapp.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.skycaster.wuhanmappingapp.StaticData;

/**
 * Created by 廖华凯 on 2017/8/2.
 */

public class BaseApplication extends Application {
    private static Handler handler;
    private static DisplayMetrics displayMetrics;
    private static Context context;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        handler=new Handler();
        sharedPreferences=context.getSharedPreferences(StaticData.SP_NAME,MODE_PRIVATE);
    }

    public static void post(Runnable runnable){
        handler.post(runnable);
    }

    public static void postDelay(Runnable runnable,long millis){
        handler.postDelayed(runnable,millis);
    }

    public static Context getGlobalContext(){
        return context;
    }

    public static void setDisplayMetrics(DisplayMetrics metrics){
        displayMetrics=metrics;
    }

    public static DisplayMetrics getDisplayMetrics(){
        return displayMetrics;
    }

    public static int getScreenWidth(){
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        return displayMetrics.heightPixels;
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }
}
