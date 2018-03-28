package com.cinema.avans.cinemaapp.frontEnd.domain;

import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.CinemaHall;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Showing {

    private int showingId;
    private CinemaHall cinemaHall;
    private Movie movie;
    private Date date;

    // SETTERS
    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }
    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    // GETTERS
    public int getShowingId() {
        return showingId;
    }
    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }
    public Movie getMovie() {
        return movie;
    }
    public Date getDate() {
        return date;
    }

}
