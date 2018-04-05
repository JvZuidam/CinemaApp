package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;
import com.cinema.avans.cinemaapp.frontEnd.logic.user.SeatSelector;
import com.cinema.avans.cinemaapp.frontEnd.logic.user.ShowingsGetter;
import com.cinema.avans.cinemaapp.frontEnd.logic.user.ShowingsListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailedActivity extends AppCompatActivity implements ShowingsListener {

    private Movie movie;
    private User user;

    private ShowingsGetter showingsGetter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Setup activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);

        setupActivity();

        // Display movie
        displayMovie();

        // Setup button to go trough to Showings
        setupShowingButton();

    }

    private void setupActivity() {

        // Get movie
        movie = (Movie) getIntent().getExtras().getSerializable("MOVIE");
        user = (User) getIntent().getSerializableExtra("USER");

        Log.i("MovieDetailedActivity", "User gotten: " + user);

        // Create ShowingsGetter
        showingsGetter = new ShowingsGetter(new RepositoryFactory(getApplicationContext()).getShowingRepository(), this);

        // Progressbar gone
        progressBar = findViewById(R.id.detailedMovieProgressBar);
        progressBar.setVisibility(View.GONE);

    }

    // Helper method to display movie information
    private void displayMovie() {

        // Header image
        ImageView movieHeaderImage = findViewById(R.id.detailedMovieHeaderImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieHeaderImage);

        // Small image
        ImageView movieImage = findViewById(R.id.detailedMovieImage);
        Picasso.with(getApplicationContext()).load(movie.getImageUrl()).into(movieImage);

        // Title
        TextView movieTitle = findViewById(R.id.detailedMovieTitle);
        movieTitle.setText(movie.getTitle());

        // Runtime
        TextView movieRuntime = findViewById(R.id.detailedMovieDurationText);
        movieRuntime.setText(movie.getRuntime());

        // Rating
        TextView movieRating = findViewById(R.id.detailedMovieRatingText);
        movieRating.setText(movie.getRating());

        // Release date
        TextView releaseDate = findViewById(R.id.detailedMovieReleaseDate);
        releaseDate.setText(movie.getReleaseDate());

        // Description
        TextView movieDescription = findViewById(R.id.detailedMovieDescription);
        movieDescription.setText(movie.getDescription());

    }

    // Helper method to setup button to go trough to Showings for this movie
    private void setupShowingButton() {

        // Setup listener
        Button showingsButton = findViewById(R.id.detailedMovieShowingsButton);
        showingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If Showings still have to be added to the Movie
                if (movie.getShowings().size() == 0) {

                    // Start loading indicator
                    startLoader();
                    // Start async task to get Showings for the Movie
                    showingsGetter.execute(movie);

                }
                // If user pressed the back button and no Showings have to be found
                else {

                    // Start the intent and pass the movie trough
                    Intent intent = new Intent(MovieDetailedActivity.this, MovieDetailedShowingsActivity.class);
                    intent.putExtra("MOVIE", movie);
                    intent.putExtra("USER", user);
                    stopLoader();
                    startActivity(intent);

                }

            }
        });

    }

    // Interface method to continue when Showings have been found
    public void showingsFound(ArrayList<Showing> showings) {

        // Add the Showings to the Movie
        movie.setShowings(showings);

        // Stop the loader
        stopLoader();

        // Start the intent and pass the movie trough
        Intent intent = new Intent(MovieDetailedActivity.this, MovieDetailedShowingsActivity.class);
        intent.putExtra("MOVIE", movie);
        intent.putExtra("USER", user);
        startActivity(intent);

    }

    // Helper methods to start and stop loader
    private void startLoader() {
        progressBar.setVisibility(View.VISIBLE);

    }
    private void stopLoader() {
        progressBar.setVisibility(View.GONE);

    }

}
