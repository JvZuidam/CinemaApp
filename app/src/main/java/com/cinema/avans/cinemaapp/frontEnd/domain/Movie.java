package com.cinema.avans.cinemaapp.frontEnd.domain;

import java.io.Serializable;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Movie implements Serializable {

    private int movieId; // Done
    private String title; // Done
    private String description; // Done
    private String imageUrl;

    // SETTERS
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // GETTERS
    public String getImageUrl() {
        return imageUrl;
    }
    public int getMovieId() {
        return movieId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String toString() {
        return "MovieId: " + movieId + ", title: " + title + ", imageUrl: " + imageUrl;
    }

}
