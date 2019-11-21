package com.miss.pojo;

/**
 * @author MissLi
 * @create 2019/4/23 17:17
 * 预约信息
 */
public class ReserveDevice {
    /*
    预约用户名
     */
    private String userName;
    /*
    预约设备名
     */
    private String deviceName;
    private String startTime;
    private String endTime;
    private String crash;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCrash() {
        return crash;
    }

    public void setCrash(String crash) {
        this.crash = crash;
    }

    @Override
    public String toString() {
        return "ReserveDevice{" +
                "userName='" + userName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", crash='" + crash + '\'' +
                '}';
    }
}
