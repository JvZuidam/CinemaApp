package com.cinema.avans.cinemaapp.frontEnd.logic.user;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatSelector implements Serializable {

    private HallInstance hallInstance;
    private ArrayList<SeatInstance> selectedSeatInstances;
    private int amountOfSeatsUserWants;

    public SeatSelector(HallInstance hallInstance
                        ,int amountOfSeatsUserWants) {
        this.hallInstance = hallInstance;
        this.amountOfSeatsUserWants = amountOfSeatsUserWants;
        this.selectedSeatInstances = new ArrayList<>();

        // Test code, remove when real data exists
        fakeCinemaHall();

    }

    public HallInstance getHallInstance() {
        return hallInstance;

    }

    // Method called by UI when user clicks a seatInstance
    public void seatClicked(SeatInstance seatInstance) {

        // Remove seatInstance is user already clicked it
        if (selectedSeatInstances.contains(seatInstance)) {
            selectedSeatInstances.remove(seatInstance);
            seatInstance.setStatus(SeatStatus.AVAILABLE);

        // Add seatInstance (only if the seatInstance is available)
        } else if (seatInstance.getStatus() == SeatStatus.AVAILABLE) {
            selectedSeatInstances.add(seatInstance);
            seatInstance.setStatus(SeatStatus.SELECTED);

        }
        // If to much seats are being selected:
        // - remove the first that user selected
        // - also set the status back to available
        if (selectedSeatInstances.size() > amountOfSeatsUserWants) {
            selectedSeatInstances.get(0).setStatus(SeatStatus.AVAILABLE);
            selectedSeatInstances.remove(0);
        }

    }

    // Test methods, create fake data
    private void fakeCinemaHall() {

        // Cinema hall
        hallInstance = new HallInstance();
        hallInstance.setHallId(1);
        // List of seat rows
        ArrayList<SeatRowInstance> seatRowInstances = new ArrayList<>();
        // Add seat rows
        seatRowInstances.add(createSeatRow(1, hallInstance));
        seatRowInstances.add(createSeatRow(2, hallInstance));
        seatRowInstances.add(createSeatRow(3, hallInstance));
        seatRowInstances.add(createSeatRow(4, hallInstance));
        seatRowInstances.add(createSeatRow(5, hallInstance));
        seatRowInstances.add(createSeatRow(6, hallInstance));
        // Set rows to cinema hall
        hallInstance.setRows(seatRowInstances);

    }
    private SeatRowInstance createSeatRow(int number, HallInstance hallInstance) {

        // Create seatRowInstance
        SeatRowInstance seatRowInstance = new SeatRowInstance();
        seatRowInstance.setRowNr(number);
        seatRowInstance.setHallInstance(hallInstance);

        // List with seatInstances
        ArrayList<SeatInstance> seatInstances = new ArrayList<>();

        // Seats and add
        for (int i = 1; i < 13; i ++) {

            if (i <= 2) {
                seatInstances.add(createSeat(i, SeatStatus.AVAILABLE, seatRowInstance));
            } else if (i == 3) {
                seatInstances.add(createSeat(i, SeatStatus.GAP, seatRowInstance));

            } else if (i <= 9) {
                seatInstances.add(createSeat(i, SeatStatus.AVAILABLE, seatRowInstance));

            } else if (i == 10){
                seatInstances.add(createSeat(i, SeatStatus.GAP, seatRowInstance));

            } else {
                seatInstances.add(createSeat(i, SeatStatus.AVAILABLE, seatRowInstance));

            }

        }

        seatRowInstance.setSeatInstances(seatInstances);

        return seatRowInstance;

    }
    private SeatInstance createSeat(int number, SeatStatus status, SeatRowInstance seatRowInstance) {

        SeatInstance seatInstance = new SeatInstance();
        seatInstance.setNr(number);
        seatInstance.setSeatRowInstance(seatRowInstance);
        seatInstance.setStatus(status);

        return seatInstance;
    }

}
