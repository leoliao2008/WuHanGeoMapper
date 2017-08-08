package com.skycaster.wuhanmappingapp.M;

import android.content.SharedPreferences;

import com.skycaster.wuhanmappingapp.StaticData;
import com.skycaster.wuhanmappingapp.base.BaseApplication;
import com.skycaster.wuhanmappingapp.interf.iModel;

import java.io.File;
import java.io.IOException;

import project.SerialPort.SerialPort;
import project.SerialPort.SerialPortFinder;

/**
 * Created by 廖华凯 on 2017/8/8.
 */

public class SerialPortModel implements iModel {

    public String[] getAvailablePaths(){
        return new SerialPortFinder().getAllDevicesPath();
    }

    public BaudRate[] getAvailableBaudRates(){
        return BaudRate.values();
    }

    public String getCurrentPath(){
        return BaseApplication.getSharedPreferences().getString(StaticData.SERIAL_PORT_PATH,"ttyAMA04");
    }

    public BaudRate getCurrentBaudRate(){
        return BaudRate.getValueByString(
                BaseApplication.getSharedPreferences().getString(StaticData.BAUD_RATE,"19200")
        );
    }

    public SerialPort openSerialPort(String path, BaudRate baudRate) throws IOException,SecurityException {
        SerialPort serialPort = new SerialPort(new File(path), baudRate.toInt(), 0);
        SharedPreferences.Editor editor = BaseApplication.getSharedPreferences().edit();
        editor.putString(StaticData.SERIAL_PORT_PATH,path);
        editor.putString(StaticData.BAUD_RATE,baudRate.toString());
        editor.apply();
        return serialPort;
    }



    @Override
    public void onDetachFromPresenter() {

    }

    public enum BaudRate{
        BD_9600,BD_19200,BD_38400,BD_57600,BD_115200;
        @Override
        public String toString() {
            return super.toString().split("_")[1];
        }

        public int toInt() {
            return Integer.valueOf(toString());}

        public static BaudRate getValueByString(String s){
            switch (s){
                case "9600":
                    return BD_9600;
                case "19200":
                    return BD_19200;
                case "38400":
                    return BD_38400;
                case "57600":
                    return BD_57600;
                case "115200":
                    return BD_115200;
                default:
                    return BD_19200;
            }
        }




    }
}
