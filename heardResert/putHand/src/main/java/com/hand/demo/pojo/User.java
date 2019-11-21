package com.hand.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MissLi
 * @create 2019/4/5 19:58
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "userName")
    private String userName;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role")
    private String role="User";

    public User(String userName, int i) {
        this.userName = userName;
        this.i = i;
    }
    public User() {}
    private int i=0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
