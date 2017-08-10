package com.skycaster.wuhanmappingapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/2.
 */

public abstract class BaseMvpActivity<P extends iPresenter> extends AppCompatActivity {

    private iPresenter mPresenter;
    private ActionBar mActionBar;
    private String mClassName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName=getClass().getSimpleName();
        setContentView(setContentViewID());
        mActionBar = getSupportActionBar();
        if(mActionBar!=null){
            initActionBar(mActionBar);
        }
        initView();
        mPresenter=initPresenter();
        mPresenter.initData();
        initListener();
        overridePendingTransition(R.anim.anim_activity_start_enter,R.anim.anim_activity_start_exit);
    }

    private void initActionBar(ActionBar actionBar){
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(setActionBarTitle());
    }

    protected abstract String setActionBarTitle();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    protected abstract int setContentViewID();

    protected abstract void initView();

    protected abstract iPresenter initPresenter();

    protected P getPresenter(){
        return (P) mPresenter;
    }

    protected abstract void initListener();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_activity_back_pressed_enter, R.anim.anim_activity_back_pressed_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachedFromView();
    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void showLog(String msg){
        Log.e(mClassName,msg);
    }
}
