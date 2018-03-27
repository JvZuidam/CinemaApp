package com.cinema.avans.cinemaapp.frontEnd.domain;

public class SeatInstance {

    private Seat seat;
    // private Showing showing;
    private boolean isReserved;

    // Showing in constructor
    public SeatInstance(Seat seat,
                        boolean isReserved) {

        this.seat = seat;
        this.isReserved = isReserved;

    }

}
