package com.skycaster.wuhanmappingapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

/**
 * Created by 廖华凯 on 2017/8/3.
 */

public class AlertDialogUtil {
    private static AlertDialog alertDialog;

    public static void showDialog(Context context, String msg, final Runnable onConfirm, @Nullable final Runnable onCancel){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("温馨提示")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onConfirm.run();
                        alertDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(onCancel!=null){
                            onCancel.run();
                        }
                        alertDialog.dismiss();
                    }

                });
        alertDialog=builder.create();
        alertDialog.show();
    }

    public static void showDialog(Context context,String msg,Runnable onConfirm){
        showDialog(context,msg,onConfirm,null);
    }
}
