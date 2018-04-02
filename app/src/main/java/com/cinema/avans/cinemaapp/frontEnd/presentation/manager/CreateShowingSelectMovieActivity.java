package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.presentation.user.MovieAdapter;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 01 April 2018
 */

public class CreateShowingSelectMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_showing_select_movie);

        // Get movies
        ArrayList<Movie> movies = new RepositoryFactory(getApplicationContext()).getMovieRepository().getAllMovies();

        // Display movies
        final MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        ListView movieListView = findViewById(R.id.createShowingSelectMovieListView);
        movieListView.setAdapter(movieAdapter);

        // Set click listener
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Send selected movie back to create showing activity
                Intent intent = new Intent();
                intent.putExtra("MOVIE", movieAdapter.getItem(i));
                setResult(2, intent);
                finish();

            }
        });

    }

}
