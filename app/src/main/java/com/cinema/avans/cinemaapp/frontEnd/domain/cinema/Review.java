package com.cinema.avans.cinemaapp.frontEnd.domain.cinema;

import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Review implements Serializable {

    private Movie movie;
    private User user;
    private Rating rating;
    private String comment;

    // SETTERS
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    // GETTERS
    public Movie getMovie() {
        return movie;
    }
    public User getUser() {
        return user;
    }
    public Rating getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {

        return "Review: " + "\n" +
                "Movie: " + movie.getTitle() + "\n" +
                "User: " + user.getUsername();

    }

}
