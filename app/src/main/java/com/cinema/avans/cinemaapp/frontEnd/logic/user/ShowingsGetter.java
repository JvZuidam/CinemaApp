package com.cinema.avans.cinemaapp.frontEnd.logic.user;

import android.os.AsyncTask;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.ShowingRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class ShowingsGetter extends AsyncTask<Movie, Void, Void> {

    private ShowingRepository showingRepository;
    private ShowingsListener showingsListener;

    public ShowingsGetter(ShowingRepository showingRepository, ShowingsListener showingsListener) {
        this.showingRepository = showingRepository;
        this.showingsListener = showingsListener;

    }

    @Override
    protected Void doInBackground(Movie... movies) {

        Movie movie = movies[0];

        showingsListener.showingsFound(showingRepository.getShowings(movie));

        return null;

    }

}
