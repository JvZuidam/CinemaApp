package com.cinema.avans.cinemaapp.frontEnd.domain;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Ticket {

    private int ticketId; // Done
    private Showing showing; // Done
    private SeatInstance seatInstance; // Done

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public SeatInstance getSeatInstance() {
        return seatInstance;
    }

    public void setSeatInstance(SeatInstance seatInstance) {
        this.seatInstance = seatInstance;
    }
}
