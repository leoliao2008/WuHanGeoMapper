package com.skycaster.wuhanmappingapp.P;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.skycaster.wuhanmappingapp.M.SerialPortModel;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.activity.SerialPortActivity;
import com.skycaster.wuhanmappingapp.base.BaseActivityPresenter;
import com.skycaster.wuhanmappingapp.service.GPGGAService;

import java.util.ArrayList;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SerialPortActivityPresenter extends BaseActivityPresenter<SerialPortActivity> {
    private SerialPortModel mSerialPortModel;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> messages=new ArrayList<>();
    private String mCurrentPath;
    private SerialPortModel.BaudRate mCurrentBaudRate;
    private AlertDialog mSpConfigDialog;
    private GPGGADataReceiver mDataReceiver;

    public SerialPortActivityPresenter(SerialPortActivity view) {
        super(view);
        mSerialPortModel=new SerialPortModel();
    }

    @Override
    public void initData() {
        mAdapter=new ArrayAdapter<String>(
                getView(),
                android.R.layout.simple_list_item_1,
                messages
        );
        getView().getListView().setAdapter(mAdapter);

        mCurrentPath = mSerialPortModel.getCurrentPath();
        mCurrentBaudRate = mSerialPortModel.getCurrentBaudRate();

        mDataReceiver=new GPGGADataReceiver();
        IntentFilter intentFilter=new IntentFilter(StaticData.ACTION_RECEIVE_BROADCASTING_GPGGA_RAW_DATA);
        LocalBroadcastManager.getInstance(getView()).registerReceiver(mDataReceiver,intentFilter);

    }

    private void updateListView(String msg){
        messages.add(msg);
        mAdapter.notifyDataSetChanged();
        getView().getListView().smoothScrollToPosition(Integer.MAX_VALUE);
    }

    public void openSerialPortConfig(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getView());
        ViewGroup viewGroup= (ViewGroup) getView().getLayoutInflater().inflate(R.layout.dialog_serial_port_config,null);
        final AppCompatSpinner spin_path=viewGroup.findViewById(R.id.dialog_sp_config_spinner_path);
        final AppCompatSpinner spin_rate=viewGroup.findViewById(R.id.dialog_sp_config_spinner_baud_rate);
        Button btn_confirm=viewGroup.findViewById(R.id.dialog_sp_config_btn_confirm);
        Button btn_cancel=viewGroup.findViewById(R.id.dialog_sp_config_btn_cancel);


        String[] paths = mSerialPortModel.getAvailablePaths();
        ArrayAdapter<String> pathAdapter=new ArrayAdapter<String>(
                getView(),
                android.R.layout.simple_spinner_item,
                paths
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        };
        pathAdapter.setDropDownViewResource(R.layout.item_drop_down_view);
        spin_path.setAdapter(pathAdapter);
        for(int i=0;i<paths.length;i++){
            if(paths[i].equals(mCurrentPath)){
                spin_path.setSelection(i);
                break;
            }
        }



        final SerialPortModel.BaudRate[] baudRates = mSerialPortModel.getAvailableBaudRates();
        ArrayAdapter<SerialPortModel.BaudRate> ratesAdapter=new ArrayAdapter<SerialPortModel.BaudRate>(
                getView(),
                android.R.layout.simple_spinner_item,
                baudRates
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setText(baudRates[position].toString());
                textView.setGravity(Gravity.CENTER);
                return textView;
            }

        };
        ratesAdapter.setDropDownViewResource(R.layout.item_drop_down_view);
        spin_rate.setAdapter(ratesAdapter);
        for(int i=0;i<baudRates.length;i++){
            if(baudRates[i].toString().equals(mCurrentBaudRate.toString())){
                spin_rate.setSelection(i);
                break;
            }
        }

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = (String) spin_path.getSelectedItem();
                SerialPortModel.BaudRate baudRate = (SerialPortModel.BaudRate) spin_rate.getSelectedItem();
                if(!TextUtils.isEmpty(path)&&baudRate!=null){

                    if(path.equals(mCurrentPath)&&baudRate.toString().equals(mCurrentBaudRate.toString())){
                        mSpConfigDialog.dismiss();
                        return;
                    }

                    LocalBroadcastManager.getInstance(getView()).sendBroadcast(new Intent(StaticData.ACTION_STOP_GPGGA_SERVICE));
                    Intent intent = new Intent(getView(), GPGGAService.class);
                    intent.putExtra(StaticData.SERIAL_PORT_PATH,path);
                    intent.putExtra(StaticData.BAUD_RATE,baudRate.toString());
                    getView().startService(intent);
                    mSpConfigDialog.dismiss();

                }else {
                    getView().showToast("请设置串口路径及波特率。");
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpConfigDialog.dismiss();
            }
        });

        mSpConfigDialog = builder.setView(viewGroup).create();
        mSpConfigDialog.show();
    }

    @Override
    public void onDetachedFromView() {
        if(mDataReceiver!=null){
            LocalBroadcastManager.getInstance(getView()).unregisterReceiver(mDataReceiver);
        }
    }

    private class GPGGADataReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] data = intent.getByteArrayExtra(StaticData.PORT_DATA_RAW);
            updateListView(new String(data));
            getView().getIv_noData().setVisibility(View.GONE);
            getView().getTv_baudRate().setText(intent.getStringExtra(StaticData.BAUD_RATE));
            getView().getTv_path().setText(intent.getStringExtra(StaticData.SERIAL_PORT_PATH));
        }
    }
}
