package com.cinema.avans.cinemaapp.frontEnd.logic.manager;

import android.util.Log;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

public class HallInstanceFactory {

    // Creates a hall instance from a given hall
    public HallInstance createHallInstance(Hall hall) {

        Log.i("HallInstanceFactory", "Creating HallInstance from: " + hall);

        // Create hall instance
        HallInstance hallInstance = new HallInstance();
        hallInstance.setHall(hall);

        // Fill hall instance with seats
        ArrayList<SeatRowInstance> seatRowInstances = new ArrayList<>();
        for (SeatRow seatRow : hall.getSeatRows()) {
            seatRowInstances.add(createSeatRowInstance(seatRow));

        }
        for (SeatRowInstance seatRowInstance : seatRowInstances) {
            seatRowInstance.setHallInstance(hallInstance);

        }
        hallInstance.setSeatRowInstances(seatRowInstances);

        Log.i("HallInstanceFactory", "HallInstance created: " + hallInstance);

        // Return hall instance
        return hallInstance;

    }

    // Creates a seat row instance from a give seat row
    private SeatRowInstance createSeatRowInstance(SeatRow seatRow) {

        // Create seat row instance
        SeatRowInstance seatRowInstance = new SeatRowInstance();
        seatRowInstance.setSeatRow(seatRow);

        // Create seat row instance its seat instances
        ArrayList<SeatInstance> seatInstances = new ArrayList<>();
        for (Seat seat : seatRow.getSeats()) {
            seatInstances.add(createSeatInstance(seatRowInstance, seat));

        }
        seatRowInstance.setSeatInstances(seatInstances);

        // Return seat row instance
        return seatRowInstance;

    }

    private SeatInstance createSeatInstance(SeatRowInstance seatRowInstance, Seat seat) {

        SeatInstance seatInstance = new SeatInstance();

        seatInstance.setSeat(seat);
        seatInstance.setSeatRowInstance(seatRowInstance);
        // When hall instance is creates all seats begin available
        seatInstance.setStatus(SeatStatus.AVAILABLE);

        return seatInstance;

    }

}
