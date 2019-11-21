package com.miss.pojo;

import java.io.Serializable;

/**
 * @author MissLi
 * @create 2019/4/23 17:18
 * 设备信息
 */
public class Device implements Serializable {
    private String deviceNumber;
    private String deviceName;
    private Double devicePrice;
    private String deviceAddress;
    private boolean deviceStatus;
    private Integer deviceCount;
    private String deviceType;
    private String deviceImageUrl;
    private String deviceDisction;

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(Double devicePrice) {
        this.devicePrice = devicePrice;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public boolean isDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(boolean deviceStatus) {
        if(this.getDeviceCount()>0) {
            this.deviceStatus = deviceStatus;
        }else{
            this.setDeviceStatus(false);
        }
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceImageUrl() {
        return deviceImageUrl;
    }

    public void setDeviceImageUrl(String deviceImageUrl) {
        this.deviceImageUrl = deviceImageUrl;
    }

    public String getDeviceDisction() {
        return deviceDisction;
    }

    public void setDeviceDisction(String deviceDisction) {
        this.deviceDisction = deviceDisction;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceNumber='" + deviceNumber + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", devicePrice=" + devicePrice +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", deviceCount=" + deviceCount +
                ", deviceType='" + deviceType + '\'' +
                ", deviceImageUrl='" + deviceImageUrl + '\'' +
                ", deviceDisction='" + deviceDisction + '\'' +
                '}';
    }
}
