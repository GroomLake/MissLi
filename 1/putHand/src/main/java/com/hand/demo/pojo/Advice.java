package com.hand.demo.pojo;

/**
 * @author MissLi
 * @create 2019/4/6 15:55
 */
public class Advice {
    private User user;
    private String status;
    private String hand="false";
    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "user=" + user +
                ", status='" + status + '\'' +
                ", hand='" + hand + '\'' +
                '}';
    }
}
