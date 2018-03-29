package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;

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

    public Hall getHall(int hallId) {

        Hall hall = databaseManager.getHall(hallId);
        // Also get seatRows! with seat row repository, otherwise seats will nog be added
        hall.setSeatRows(new SeatRowRepository(databaseManager).getSeatRows(hall));

        return hall;

    }

}
