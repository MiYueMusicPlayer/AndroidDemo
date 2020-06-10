package com.gongw.remote.communication;

/**
 * 定义通信用的一些标识
 * Created by gw on 2017/7/28.
 */
public class CommunicationKey {
    public static final String RESPONSE_OK = "ok";
    public static final String RESPONSE_ECHO = "echo:";
    public static final String RESPONSE_ERROR = "error:";
    public static final String EOF = new String(new byte[]{10,11,12,13});
}
