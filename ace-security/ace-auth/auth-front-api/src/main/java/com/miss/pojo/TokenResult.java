package com.miss.pojo;

import java.io.Serializable;

/**
 * @author MissLi
 * @create 2019/4/24 21:38
 */
public class TokenResult implements Serializable {
    private boolean flag=true;
    private String massage="";
    private String token="";

    @Override
    public String toString() {
        return "TokenResult{" +
                "flag=" + flag +
                ", massage='" + massage + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
