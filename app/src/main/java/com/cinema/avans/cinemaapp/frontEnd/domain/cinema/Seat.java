package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Seat {

    private int seatId; // Done
    private SeatRow seatRow; // Done
    private int seatNr; // Done
    private SeatValue seatValue; // Done

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public SeatRow getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(SeatRow seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNr() {
        return seatNr;
    }

    public void setSeatNr(int seatNr) {
        this.seatNr = seatNr;
    }

    public SeatValue getSeatValue() {
        return seatValue;
    }

    public void setSeatValue(SeatValue seatValue) {
        this.seatValue = seatValue;
    }
    public int getSeatValueInt() {

        if (seatValue == SeatValue.PERFECT) {
            return 5;

        } else if (seatValue == SeatValue.GOOD) {
            return 4;

        } else if (seatValue == SeatValue.OK) {
            return 3;

        } else if (seatValue == SeatValue.MODERATE) {
            return 2;

        } else if (seatValue == SeatValue.BAD) {
            return 1;

        }

        return 0;

    }

    public void setValue(int valueInt) {

        if (valueInt == 5) {
            seatValue = SeatValue.PERFECT;

        } else if (valueInt == 4) {
            seatValue = SeatValue.GOOD;

        } else if (valueInt == 3) {
            seatValue = SeatValue.OK;

        } else if (valueInt == 2) {
            seatValue = SeatValue.MODERATE;

        } else if (valueInt == 1) {
            seatValue = SeatValue.BAD;

        }

    }
}
