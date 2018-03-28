package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;

public class Seat implements Serializable {

    private int seatId;
    private SeatRow row;
    private int nr;
    private SeatValue value;

    // Test only (normally in SeatInstance)
    private SeatStatus status;

    // SETTERS
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public void setRow(SeatRow row) {
        this.row = row;
    }
    public void setNr(int nr) {
        this.nr = nr;
    }
    public void setValue(SeatValue value) {
        this.value = value;
    }
    // GETTERS
    public int getSeatId() {
        return seatId;
    }
    public SeatRow getRow() {
        return row;
    }
    public int getNr() {
        return nr;
    }
    public SeatValue getValue() {
        return value;
    }

    // Test only (normally in SeatInstance)
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    public SeatStatus getStatus() {
        return status;
    }

    // EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (nr != seat.nr) return false;
        return row != null ? row.equals(seat.row) : seat.row == null;
    }
    @Override
    public int hashCode() {
        int result = row != null ? row.hashCode() : 0;
        result = 31 * result + nr;
        return result;
    }

    // ToString (test method)
    @Override
    public String toString() {
        return "Row: " + row.getRowNr() + " | Nr: " + this.nr + " | Status : " + status;

    }

}
