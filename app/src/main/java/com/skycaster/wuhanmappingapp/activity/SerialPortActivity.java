package com.skycaster.wuhanmappingapp.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.P.SerialPortActivityPresenter;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.base.BaseMvpActivity;
import com.skycaster.wuhanmappingapp.interf.iPresenter;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SerialPortActivity extends BaseMvpActivity<SerialPortActivityPresenter> {
    private ListView mListView;
    private TextView tv_path;
    private TextView tv_baudRate;
    private ImageView iv_noData;


    public static void start(Context context) {
        Intent starter = new Intent(context, SerialPortActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_serial_port;
    }

    @Override
    protected void initView() {
        mListView= (ListView) findViewById(R.id.serial_port_setting_lst_view);
        tv_path= (TextView) findViewById(R.id.serial_port_setting_tv_path);
        tv_baudRate= (TextView) findViewById(R.id.serial_port_setting_tv_rate);
        iv_noData= (ImageView) findViewById(R.id.serial_port_setting_iv_no_data);

    }

    public ListView getListView() {
        return mListView;
    }

    public TextView getTv_path() {
        return tv_path;
    }

    public TextView getTv_baudRate() {
        return tv_baudRate;
    }

    public ImageView getIv_noData() {
        return iv_noData;
    }

    @Override
    protected iPresenter initPresenter() {
        return new SerialPortActivityPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_serial_port,menu);
        return true;
    }

    @Override
    protected String setActionBarTitle() {
        return "串口设置";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_serial_port_ic_setting:
                getPresenter().openSerialPortConfig();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
