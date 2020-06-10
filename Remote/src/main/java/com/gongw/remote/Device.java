package com.gongw.remote;

import com.google.gson.annotations.SerializedName;

/**
 * 局域网中的设备
 */
public class Device {
    //ip地址
    private String ip;
    //端口号
    private int port;
    //唯一id
    private String uuid;
    //上次心跳时间
    private long lastUpdateTime;
    private String deviceName;
    private String serial;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int arg1;
    public int arg2;
    private boolean isChecked;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    private String icon;
    private String title;
    private String signer;
    private int volume;
    private int maxVolume;
    private String isUpdating="2";

    public String getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(String isUpdating) {
        this.isUpdating = isUpdating;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    /**
     * isInNet :
     * lastUpdateTime : 0
     * netKey :
     */

    private String isInNet;

    private String netKey="";

    public Device(String uuid, String deviceName){
        this.uuid = uuid;
        this.deviceName = deviceName;
    }

    public Device(String ip, int port, String uuid) {
        this.ip = ip;
        this.port = port;
        this.uuid = uuid;
    }

    public Device(String ip, int port, String uuid, String deviceName){
        this.ip = ip;
        this.port = port;
        this.uuid = uuid;
        this.deviceName = deviceName;
    }

    public Device(String ip, int port){
        this.ip = ip;
        this.port = port;
    }


    /**
     * 更新device类内的对象数据
     * @param device
     */
    public void updateData(Device device){
        ip = device.ip;
        port = device.port;
        uuid = device.uuid;
        deviceName = device.deviceName;
        arg1 = device.arg1;
        arg2 = device.arg2;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public boolean equals(Object obj) {
        if(uuid == null || obj== null){
            return false;
        }
        if(uuid.equals(((Device)obj).getUuid())){
            return true;
        }
        return false;
    }

    public String getIsInNet() {
        return isInNet;
    }

    public void setIsInNet(String isInNet) {
        this.isInNet = isInNet;
    }


    public String getNetKey() {
        return netKey;
    }

    public void setNetKey(String netKey) {
        this.netKey = netKey;
    }
}
