package com.miss.pojo;

import java.io.Serializable;

/**
 * @author MissLi
 * @create 2019/4/24 20:58
 * token授权
 */
public class Token implements Serializable {
    private String userName;
    private String tokenTime;
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(String tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userName='" + userName + '\'' +
                ", tokenTime='" + tokenTime + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
