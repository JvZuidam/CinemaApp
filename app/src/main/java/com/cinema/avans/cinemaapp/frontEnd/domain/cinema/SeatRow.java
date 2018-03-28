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

    // EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatRow seatRow = (SeatRow) o;

        if (rowNr != seatRow.rowNr) return false;
        return cinemaHall != null ? cinemaHall.equals(seatRow.cinemaHall) : seatRow.cinemaHall == null;
    }
    @Override
    public int hashCode() {
        int result = cinemaHall != null ? cinemaHall.hashCode() : 0;
        result = 31 * result + rowNr;
        return result;
    }

}
