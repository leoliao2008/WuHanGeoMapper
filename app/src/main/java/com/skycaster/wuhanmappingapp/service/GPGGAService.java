package com.skycaster.wuhanmappingapp.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.skycaster.wuhanmappingapp.M.SerialPortModel;
import com.skycaster.wuhanmappingapp.R;
import com.skycaster.wuhanmappingapp.StaticData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import project.SerialPort.SerialPort;

import static com.skycaster.wuhanmappingapp.StaticData.GPGGA_DATA_BUFFER_SIZE;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class GPGGAService extends Service {
    private SerialPort mSerialPort;
    private GPGGAServiceTerminateReceiver mReceiver;
    private byte[] temp=new byte[GPGGA_DATA_BUFFER_SIZE];
    private AtomicBoolean isContinue=new AtomicBoolean(false);
    private String mPath;
    private String mBaudRate;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initForegroundNotice();

        initBroadCastReceiver();

        SerialPortModel serialPortModel=new SerialPortModel();
        mPath = intent.getStringExtra(StaticData.SERIAL_PORT_PATH);
        mBaudRate = intent.getStringExtra(StaticData.BAUD_RATE);
        try {
            mSerialPort = serialPortModel.openSerialPort(mPath, SerialPortModel.BaudRate.getValueByString(mBaudRate));
            receivePortData(mSerialPort.getInputStream());
        } catch (IOException e) {
            showToast("串口路径有误，无法打开串口。");
            stopReceivingPortData();
        } catch (SecurityException e){
            showToast("无法打开串口，请确认串口权限。");
            stopReceivingPortData();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void initBroadCastReceiver() {
        mReceiver=new GPGGAServiceTerminateReceiver();
        IntentFilter intentFilter=new IntentFilter(StaticData.ACTION_STOP_GPGGA_SERVICE);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver,intentFilter);
    }

    private void initForegroundNotice() {
        Notification.Builder builder=new Notification.Builder(getApplicationContext());
        Notification notice=builder
                .setSmallIcon(R.drawable.ic_receiving_radio)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_radio_signal))
                .setContentTitle(getString(R.string.app_name))
                .setContentText("正在持续监听CDRadio定位数据...")
                .build();
        startForeground(StaticData.GPGGA_SERVICE_FOREGROUND_ID,notice);
    }


    private void receivePortData(final InputStream inputStream) {
        isContinue.compareAndSet(false,true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isContinue.get()){
                        int len=inputStream.read(temp);
                        if(len>0){
                            Intent intent=new Intent(StaticData.ACTION_RECEIVE_BROADCASTING_GPGGA_RAW_DATA);
                            intent.putExtra(StaticData.PORT_DATA_RAW, Arrays.copyOf(temp,len));
                            intent.putExtra(StaticData.SERIAL_PORT_PATH,mPath);
                            intent.putExtra(StaticData.BAUD_RATE,mBaudRate);
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void stopReceivingPortData(){
        isContinue.compareAndSet(true,false);
        if(mSerialPort!=null){
            try {
                mSerialPort.getInputStream().close();
                mSerialPort.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopForeground(true);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mReceiver!=null){
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        }
    }

    private class GPGGAServiceTerminateReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            stopReceivingPortData();
        }
    }

    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }


}
