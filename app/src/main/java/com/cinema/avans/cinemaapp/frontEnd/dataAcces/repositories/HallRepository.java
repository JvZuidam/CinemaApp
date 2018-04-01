package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class HallRepository {

    DatabaseManager databaseManager;

    public HallRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public void createHall(Hall hall) {

        databaseManager.createHall(hall);

    }

    public void createEntireHall(Hall hall) {

        databaseManager.createHall(hall);

        for (SeatRow seatRow : hall.getSeatRows()) {

            databaseManager.createSeatRow(seatRow);

            for (Seat seat : seatRow.getSeats()) {

                databaseManager.createSeat(seat);

            }

        }

    }

    public ArrayList<Hall> getAllHalls() {

        return databaseManager.getAllHalls();

    }

    public Hall getHall(int hallId) {

        Hall hall = databaseManager.getHall(hallId);
        // Also get seatRows! with seat row repository, otherwise seats will nog be added
        hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        return hall;

    }

}
