package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;

public class SeatInstance implements Serializable {

    private Seat seat;
    // private Showing showing;
    private SeatStatus status;

    // SETTERS
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    // GETTERS
    public Seat getSeat() {
        return seat;
    }
    public SeatStatus getStatus() {
        return status;
    }

}
