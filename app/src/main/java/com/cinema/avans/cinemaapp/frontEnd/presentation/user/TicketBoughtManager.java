package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Ticket;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 05 April 2018
 */

public class TicketBoughtManager extends AsyncTask<Ticket, Void, Void> {

    private TicketManagerFinishedListener ticketManagerFinishedListener;
    private RepositoryFactory repositoryFactory;

    private ArrayList<SeatInstance> seatInstances;

    public TicketBoughtManager(TicketManagerFinishedListener ticketManagerFinishedListener,
                               RepositoryFactory repositoryFactory) {

        this.ticketManagerFinishedListener = ticketManagerFinishedListener;
        this.repositoryFactory = repositoryFactory;

    }

    public void setSeatInstances(ArrayList<SeatInstance> seatInstances) {

        this.seatInstances = seatInstances;

    }

    @Override
    protected Void doInBackground(Ticket... tickets) {

        // Create ticket
        for (Ticket ticket : tickets) {
            repositoryFactory.getTicketRepository().createTicket(ticket);
        }

        // Update Seats to RESERVED in database
        SeatInstanceRepository seatInstanceRepository = repositoryFactory.getSeatInstanceRepositoryFactory();
        for (SeatInstance seatInstance : seatInstances) {
            seatInstance.setStatus(SeatStatus.RESERVED);
        }
        seatInstanceRepository.updateSeats(seatInstances);

        return null;

    }

    @Override
    protected void onPostExecute(Void v) {

        ticketManagerFinishedListener.ticketManagerFinished();

    }

}
