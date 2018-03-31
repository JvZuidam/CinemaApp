package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
import com.cinema.avans.cinemaapp.frontEnd.presentation.user.MovieAdapter;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 31 March 2018
 */

public class CreateShowingActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing);

        RepositoryFactory repositoryFactory = new RepositoryFactory(getApplicationContext());
        ArrayList<Movie> movies = repositoryFactory.getMovieRepository().getAllMovies();

        movieAdapter = new MovieAdapter(getApplicationContext(), movies);

        ListView movieListView = findViewById(R.id.createShowingListView);
        movieListView.setAdapter(movieAdapter);

    }

}
