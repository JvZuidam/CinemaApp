package com.cinema.avans.cinemaapp.frontEnd.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.logic.SeatSelector;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatSelectorActivity extends AppCompatActivity {

    private SeatSelector seatSelector;
    private SeatAdapter seatAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selector);

        // Getting seat selector
        if (getIntent().getExtras() != null) {
            seatSelector = (SeatSelector) getIntent().getExtras().getSerializable("SEAT_SELECTOR");

            if (seatSelector.getHallInstance() != null) {

                displayHallInformation();

                setUpGridView();
            }
        }

    }

    private void displayHallInformation() {

        HallInstance hall = seatSelector.getHallInstance();

        TextView totalCapacity = findViewById(R.id.selectorCapacityText);
        totalCapacity.setText("Total capacity: " + hall.amountOfSeats());
        TextView availableSeats = findViewById(R.id.selectorAvailableText);
        availableSeats.setText(hall.amountOfAvailableSeats() + " seats still available");

    }

    private void setUpGridView() {

        // Adapter to display a cinemaHall in a gridView
        seatAdapter = new SeatAdapter(this, seatSelector.getHallInstance());

        // GridView with properties
        GridView gridView = findViewById(R.id.selectorHallGridView);
        gridView.setNumColumns(seatSelector.getHallInstance().amountOfSeatsInARow());
        gridView.setAdapter(seatAdapter);

        // OnItemClickListener to select a given seat
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Get clicked seat from adapter
                SeatInstance seatInstanceClicked = seatAdapter.getItem(position);
                // Notify selector that seat has been clicked (also pass that seat)
                seatSelector.seatClicked(seatInstanceClicked);
                // Notify the adapter that data has changes (display updated hall)
                seatAdapter.notifyDataSetChanged();

            }
        });

    }

}
