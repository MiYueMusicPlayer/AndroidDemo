package com.gongw.remote.communication.host;


import com.gongw.remote.MessageEvent;

/**
 * Created by Administrator on 2019-09-30.
 */

public class CallBackUtil {
    private static CallBack mCallBack;

    public static CallBack getmCallBack() {
        return mCallBack;
    }

    /**
     * 注册回调接口
     * @param callBack 一个继承回调接口的对象
     */
    public static void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    /**
     * @param message 传递的信息
     */
    public static void doCallBack(MessageEvent message){
        mCallBack.getData(message);
    }

}
