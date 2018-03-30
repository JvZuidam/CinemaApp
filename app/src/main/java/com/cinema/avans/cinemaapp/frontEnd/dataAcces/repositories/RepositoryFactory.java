package com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories;

import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class RepositoryFactory {

    DatabaseManager databaseManager;

    public RepositoryFactory(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    public HallRepository getHallRepository() {
        return new HallRepository(databaseManager);

    }

    public SeatRowRepository getSeatRowRepository() {
        return new SeatRowRepository(databaseManager);

    }

    public SeatRepository getSeatRepository() {
        return new SeatRepository(databaseManager);

    }

    public HallInstanceRepository getHallInstanceRepository() {
        return new HallInstanceRepository(databaseManager);

    }

    public SeatRowInstanceRepository getSeatRowInstanceRepository() {
        return new SeatRowInstanceRepository(databaseManager);

    }

    public SeatInstanceRepository getSeatInstanceRepositoryFactory() {
        return new SeatInstanceRepository(databaseManager);

    }

    public MovieRepository getMovieRepository(NewMovieListener newMovieListener) {
        return new MovieRepository(databaseManager, newMovieListener);

    }

    public ShowingRepository getShowingRepository() {
        return new ShowingRepository(databaseManager);

    }

    public TicketRepository getTicketRepository() {
        return new TicketRepository(databaseManager);

    }

    public LogInRepository getLogInRepository() {
        return new LogInRepository(databaseManager);

    }

    public UserRepository getUserRepository() {
        return new UserRepository(databaseManager);

    }

    public ManagerRepository getManagerRepository() {
        return new ManagerRepository(databaseManager);

    }

}
