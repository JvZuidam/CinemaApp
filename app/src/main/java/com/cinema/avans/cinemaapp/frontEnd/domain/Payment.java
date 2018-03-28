package com.cinema.avans.cinemaapp.frontEnd.domain;

import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Payment {

    private int paymentId;
    private User user;
    private int amount;

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

}
