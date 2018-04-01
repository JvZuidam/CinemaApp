package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Ticket implements Serializable {

    private int ticketId; // Done
    private Showing showing; // Done
    private SeatInstance seatInstance; // Done

    public Ticket() {

        this.ticketId = 0;
        this.showing = null;
        this.seatInstance = null;

    }

    // SETTERS
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public void setShowing(Showing showing) {
        this.showing = showing;
    }
    public void setSeatInstance(SeatInstance seatInstance) {
        this.seatInstance = seatInstance;
    }

    // GETTERS
    public int getTicketId() {
        return ticketId;
    }
    public Showing getShowing() {
        return showing;
    }
    public SeatInstance getSeatInstance() {
        return seatInstance;
    }

}
