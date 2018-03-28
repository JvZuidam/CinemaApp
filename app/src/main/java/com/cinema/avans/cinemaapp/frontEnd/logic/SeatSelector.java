package com.cinema.avans.cinemaapp.frontEnd.logic;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.CinemaHall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatSelector implements Serializable {

    private CinemaHall cinemaHall;
    private ArrayList<Seat> selectedSeats;
    private int amountOfSeatsUserWants;

    public SeatSelector(CinemaHall cinemaHall
                        ,int amountOfSeatsUserWants) {
        this.cinemaHall = cinemaHall;
        this.amountOfSeatsUserWants = amountOfSeatsUserWants;
        this.selectedSeats = new ArrayList<>();

        // Test code, remove when real data exists
        fakeCinemaHall();

    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;

    }

    // Method called by UI when user clicks a seat
    public void seatClicked(Seat seat) {

        // Remove seat is user already clicked it
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            seat.setStatus(SeatStatus.AVAILABLE);

        // Add seat (only if the seat is available)
        } else if (seat.getStatus() == SeatStatus.AVAILABLE) {
            selectedSeats.add(seat);
            seat.setStatus(SeatStatus.SELECTED);

        }
        // If to much seats are being selected:
        // - remove the first that user selected
        // - also set the status back to available
        if (selectedSeats.size() > amountOfSeatsUserWants) {
            selectedSeats.get(0).setStatus(SeatStatus.AVAILABLE);
            selectedSeats.remove(0);
        }

    }

    // Test methods, create fake data
    private void fakeCinemaHall() {

        // Cinema hall
        cinemaHall = new CinemaHall();
        cinemaHall.setHallId(1);
        // List of seat rows
        ArrayList<SeatRow> seatRows = new ArrayList<>();
        // Add seat rows
        seatRows.add(createSeatRow(1, cinemaHall));
        seatRows.add(createSeatRow(2, cinemaHall));
        seatRows.add(createSeatRow(3, cinemaHall));
        seatRows.add(createSeatRow(4, cinemaHall));
        seatRows.add(createSeatRow(5, cinemaHall));
        seatRows.add(createSeatRow(6, cinemaHall));
        // Set rows to cinema hall
        cinemaHall.setRows(seatRows);

    }
    private SeatRow createSeatRow(int number, CinemaHall cinemaHall) {

        // Create seatRow
        SeatRow seatRow = new SeatRow();
        seatRow.setRowNr(number);
        seatRow.setCinemaHall(cinemaHall);

        // List with seats
        ArrayList<Seat> seats = new ArrayList<>();

        // Seats and add
        for (int i = 1; i < 13; i ++) {

            if (i <= 2) {
                seats.add(createSeat(i, SeatStatus.AVAILABLE, seatRow));
            } else if (i == 3) {
                seats.add(createSeat(i, SeatStatus.GAP, seatRow));

            } else if (i <= 9) {
                seats.add(createSeat(i, SeatStatus.AVAILABLE, seatRow));

            } else if (i == 10){
                seats.add(createSeat(i, SeatStatus.GAP, seatRow));

            } else {
                seats.add(createSeat(i, SeatStatus.AVAILABLE, seatRow));

            }

        }

        seatRow.setSeats(seats);

        return seatRow;

    }
    private Seat createSeat(int number, SeatStatus status, SeatRow seatRow) {

        Seat seat = new Seat();
        seat.setNr(number);
        seat.setRow(seatRow);
        seat.setStatus(status);

        return seat;
    }

}
