package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Payment implements Serializable {

    private int paymentId;
    private User user;
    private int amount;

    public Payment() {

        this.paymentId = 0;
        this.user = null;
        this.amount = 0;

    }

    // SETTERS
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // GETTERS
    public int getPaymentId() {
        return paymentId;
    }
    public User getUser() {
        return user;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {

        return "Payment:" + "\n" +
                "- User: " + user.getUsername() + "\n" +
                "- Amount: " + amount;

    }

}
