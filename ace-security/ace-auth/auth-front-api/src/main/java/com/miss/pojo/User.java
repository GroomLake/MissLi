package com.miss.pojo;

import java.io.Serializable;

/**
 * @author MissLi
 * @create 2019/4/23 17:15
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private String telphone;
    private String projectTeam;
    private String department;

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", telphone='" + telphone + '\'' +
                ", projectTeam='" + projectTeam + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
