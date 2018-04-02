package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getExtras().getSerializable("MOVIE");

        ImageView movieHeaderImage = findViewById(R.id.detailedMovieHeaderImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieHeaderImage);
        ImageView movieImage = findViewById(R.id.detailedMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);

        TextView movieTitle = findViewById(R.id.detailedMovieTitle);
        movieTitle.setText(movie.getTitle());

        TextView movieDescription = findViewById(R.id.detailedMovieDescription);
        movieDescription.setText(movie.getDescription());

        ArrayList<Showing> showings = new RepositoryFactory(getApplicationContext()).getShowingRepository().getShowings(movie);

        // Sorter for time
        ListView movieShowingsListView = findViewById(R.id.detailedMovieShowingListView);
        ShowingsAdapter showingsAdapter = new ShowingsAdapter(getApplicationContext(), showings);
        movieShowingsListView.setAdapter(showingsAdapter);

        movieShowingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Order screen

            }
        });


    }
}
