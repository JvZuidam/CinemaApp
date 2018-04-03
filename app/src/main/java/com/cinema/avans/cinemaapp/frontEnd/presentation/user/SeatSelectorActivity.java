package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.logic.user.SeatSelector;

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

        createSpinner();

        createOkButton();

    }

    private void createSpinner() {

        String[] amountOfSeats = new String[]{"1", "2", "3", "4", "5"};

        Spinner amountOfSeatSpinner = findViewById(R.id.selectorAmountOfSeatsSpinner);
        amountOfSeatSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, amountOfSeats));
        amountOfSeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    seatSelector.setAmount(1);

                } else if (i == 1) {
                    seatSelector.setAmount(2);

                } else if (i == 2) {
                    seatSelector.setAmount(3);

                } else if (i == 3) {
                    seatSelector.setAmount(4);

                } else if (i == 4) {
                    seatSelector.setAmount(5);

                }

                seatAdapter.notifyDataSetChanged();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void createOkButton() {

        Button okButton = findViewById(R.id.selectorOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!seatSelector.isValid()) {
                    Toast.makeText(getApplicationContext(), "Select " + seatSelector.getAmount() + " seats first!", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(SeatSelectorActivity.this, PayActivity.class);
                    intent.putExtra("SELECTED_SEATS", seatSelector.getSelectedSeats());
                    intent.putExtra("SHOWING", seatSelector.getShowing());
                    startActivity(intent);

                }

            }
        });

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
