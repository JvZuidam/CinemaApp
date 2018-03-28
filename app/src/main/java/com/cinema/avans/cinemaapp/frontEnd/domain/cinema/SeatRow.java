package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatRow implements Serializable {

    private CinemaHall cinemaHall;
    private int rowNr;
    private ArrayList<Seat> seats;

    // Returns the amount of seats in the row
    public int getAmountOfSeats() {
        return seats.size();

    }

    // SETTERS
    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    // GETTERS
    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }
    public ArrayList<Seat> getSeats() {
        return this.seats;
    }
    public int getRowNr() {
        return rowNr;
    }

}
