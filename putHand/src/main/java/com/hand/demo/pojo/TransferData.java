package com.hand.demo.pojo;

/**
 * @author MissLi
 * @create 2019/4/7 14:00
 */
public class TransferData {
    private String userName;
    private String putHand;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPutHand() {
        return putHand;
    }

    public void setPutHand(String putHand) {
        this.putHand = putHand;
    }

    @Override
    public String toString() {
        return "TransferData{" +
                "userName='" + userName + '\'' +
                ", putHand='" + putHand + '\'' +
                '}';
    }
}
