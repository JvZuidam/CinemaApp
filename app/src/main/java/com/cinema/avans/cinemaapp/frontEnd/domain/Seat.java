package com.cinema.avans.cinemaapp.frontEnd.domain;

public class Seat {

    private int seatId;
    private CinemaHall cinemaHall;
    private int row;
    private int nr;
    private int value;

    public Seat(int seatId, CinemaHall cinemaHall,
                int row,
                int nr,
                int value) {

        this.seatId = seatId;
        this.cinemaHall = cinemaHall;
        this.row = row;
        this.nr = nr;
        this.value = value;

    }

}
