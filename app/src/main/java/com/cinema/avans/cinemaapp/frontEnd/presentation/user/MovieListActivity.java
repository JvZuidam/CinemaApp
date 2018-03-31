package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.NewMovieListener;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.HallRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatValue;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class MovieListActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Create repository factory and get movies
        repositoryFactory = new RepositoryFactory(getApplicationContext());
        ArrayList<Movie> movies = repositoryFactory.getMovieRepository().getAllMovies();
        Log.i("MovieListActivity", "Movie 1, Title: " + movies.get(0).getTitle());

        ListView movieListView = findViewById(R.id.movieListView);
        movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        movieListView.setAdapter(movieAdapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MovieListActivity.this, MovieDetailedActivity.class);
                intent.putExtra("MOVIE", movieAdapter.getItem(i));
                startActivity(intent);

            }
        });

    }

    // Test method to add some data to database
    private void addHalls() {

        // Creating database
        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext(), "Cinema", null, 1);

        // HALL 1 WITH ROWS AND SEATS
        HallRepository hallRepository = new HallRepository(databaseManager);
        Hall hall1 = createHall(1, 10, 12);
        hallRepository.createHall(hall1);

    }
    private Hall createHall(int hallId, int amountOfRows, int amountOfSeatsInARow) {

        // Create hall
        Hall hall = new Hall();
        hall.setHallId(hallId); // PK

        // Add seats
        ArrayList<SeatRow> seatRows = new ArrayList<>();
        for (int i = 0; i <= amountOfRows; i++) {

            seatRows.add(createSeatRow(hallId * i, i, hall, amountOfSeatsInARow));

        }

        hall.setSeatRows(seatRows);

        return hall;

    }
    private SeatRow createSeatRow(int rowId, int rowNr, Hall hall, int amountOfSeatsInARow) {

        // Create seat row
        SeatRow seatRow = new SeatRow();
        seatRow.setRowId(rowId); // PK
        seatRow.setHall(hall); // Link to hall
        seatRow.setRowNr(rowNr); // Row nr

        // Add seats
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 0; i <= amountOfSeatsInARow; i++) {

            seats.add(createSeat(i * rowId, seatRow, i, SeatValue.OK));

        }

        seatRow.setSeats(seats);

        // Return complete seat row
        return seatRow;

    }
    private Seat createSeat(int seatId, SeatRow seatRow, int seatNr, SeatValue seatValue) {

        Seat seat = new Seat();
        seat.setSeatId(seatId);
        seat.setSeatRow(seatRow);
        seat.setSeatNr(seatNr);
        seat.setSeatValue(seatValue);
        return seat;

    }

}
