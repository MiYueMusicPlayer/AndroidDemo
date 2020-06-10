package com.miyue.miyueapp.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.miyue.miyueapp.MiYueApplication;

public  class ToastUtils {
    private static Toast mToast;

    /**
     * 弹出框
     * @param msg 文字信息，必须写在string中
     * @param position 弹出位置控制
     */
    public static void showToast(int msg, int position) {
        if (mToast == null) {
            mToast = Toast.makeText(MiYueApplication.applicationContext, getString(msg), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(getString(msg));
        }
        mToast.setGravity(position,0,0);
        mToast.show();
    }

    /**
     * 弹出框
     * @param msg 文字信息，必须写在string中
     * @param position 弹出位置控制
     */
    public static void showToast(String msg, int position) {
        if (mToast == null) {
            mToast = Toast.makeText(MiYueApplication.applicationContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.setGravity(position,0,0);
        mToast.show();
    }
    public static final String getString(@StringRes int resId) {
        return MiYueApplication.applicationContext.getResources().getString(resId);
    }
}
