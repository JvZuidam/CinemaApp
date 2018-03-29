package com.cinema.avans.cinemaapp.frontEnd.dataAcces;

import com.cinema.avans.cinemaapp.frontEnd.domain.Ticket;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class TicketRepository {

    private DatabaseManager databaseManager;

    public TicketRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    public Ticket getTicket(int ticketId) {
        return databaseManager.getTicket(ticketId);

    }

}
