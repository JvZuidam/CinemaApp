package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import java.io.Serializable;

public class SeatInstance implements Serializable {

    private int seatInstanceId; // Done
    private int seatId; // Done
    private SeatRowInstance seatRowInstance; // Done
    private int nr;
    private SeatValue value;
    private SeatStatus status; // Done

    // SETTERS
    public void setSeatInstanceId(int seatInstanceId) {
        this.seatInstanceId = seatInstanceId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public void setSeatRowInstance(SeatRowInstance seatRowInstance) {
        this.seatRowInstance = seatRowInstance;
    }
    public void setNr(int nr) {
        this.nr = nr;
    }
    public void setValue(SeatValue value) {
        this.value = value;
    }
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    public void setStatus(int statusInt) {

        if (statusInt == 1) {
            status = SeatStatus.AVAILABLE;

        } else if (statusInt == 2) {
            status = SeatStatus.RESERVED;

        } else if (statusInt == 3) {
            status = SeatStatus.GAP;

        } else {
            status = SeatStatus.SELECTED;

        }

    }
    // GETTERS
    public int getSeatInstanceId() {
        return this.seatInstanceId;
    }
    public int getSeatId() {
        return seatId;
    }
    public SeatRowInstance getSeatRowInstance() {
        return seatRowInstance;
    }
    public int getNr() {
        return nr;
    }
    public SeatValue getValue() {
        return value;
    }
    public SeatStatus getStatus() {
        return status;
    }
    public int getStatusInt() {

        if (status == SeatStatus.AVAILABLE) {
            return 1;

        } else if (status == SeatStatus.RESERVED) {
            return 2;

        } else if (status == SeatStatus.GAP) {
            return 3;

        }

        return 0;

    }

}
