package com.cinema.avans.cinemaapp.frontEnd.presentation.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatValue;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 04 April 2018
 */

public class CreateHallActivity extends AppCompatActivity {

    private RepositoryFactory repositoryFactory;

    private int amountOfRows;
    private int amountOfSeatsInEachRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hall);

        repositoryFactory = new RepositoryFactory(getApplicationContext());

        amountOfRows = 0;
        amountOfSeatsInEachRow = 0;

        createRowSpinner();

        createSeatSpinner();

        createSaveButton();

    }

    private void createRowSpinner() {

        final Integer[] rows = new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

        Spinner rowSpinner = findViewById(R.id.createHallAmountOfRowsSpinner);
        rowSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, rows));
        rowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                amountOfRows = rows[i];
                Log.i("CreateHallActivity", "Amount of rows in hall: " + amountOfRows);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void createSeatSpinner() {

        final Integer[] seats = new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16};

        Spinner rowSpinner = findViewById(R.id.createHallAmountOfSeatsSpinner);
        rowSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, seats));
        rowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                amountOfSeatsInEachRow = seats[i];
                Log.i("CreateHallActivity", "Amount of seats in each row: " + amountOfSeatsInEachRow);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void createSaveButton() {

        Button saveButton = findViewById(R.id.createHallSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText hallNrInput = findViewById(R.id.createHallHallNrInput);
                int hallNr = Integer.parseInt(String.valueOf(hallNrInput.getText()));

                Hall hall = new Hall();
                hall.setHallNr(hallNr);

                ArrayList<SeatRow> seatRows = new ArrayList<>();
                for (int i = 0; i < amountOfRows; i++) {

                    SeatRow seatRow = new SeatRow();
                    seatRow.setRowId(i);
                    seatRow.setRowNr(i + 1);
                    seatRow.setHall(hall);

                    ArrayList<Seat> seats = new ArrayList<>();
                    for (int j = 0; j < amountOfSeatsInEachRow; j++ ) {

                        Seat seat = new Seat();
                        seat.setSeatRow(seatRow);
                        seat.setSeatValue(SeatValue.OK);
                        seat.setSeatNr(j + 1);
                        seats.add(seat);

                    }
                    seatRow.setSeats(seats);

                    seatRows.add(seatRow);

                }
                hall.setSeatRows(seatRows);

                repositoryFactory.getHallRepository().createHall(hall);

                Toast.makeText(getApplicationContext(), R.string.hallAdded, Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

}
