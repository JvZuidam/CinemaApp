package com.cinema.avans.cinemaapp.frontEnd.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.SeatInstanceRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 03 April 2018
 */

public class PayActivity extends AppCompatActivity {

    private ArrayList<SeatInstance> seatInstancesForUser;
    private Showing showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        seatInstancesForUser = (ArrayList<SeatInstance>) getIntent().getSerializableExtra("SELECTED_SEATS");
        showing = (Showing) getIntent().getSerializableExtra("SHOWING");

        Button payButton = findViewById(R.id.payPayButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SeatInstanceRepository seatInstanceRepository = new RepositoryFactory(getApplicationContext()).getSeatInstanceRepositoryFactory();
                for (SeatInstance seatInstance : seatInstancesForUser) {
                    seatInstance.setStatus(SeatStatus.RESERVED);
                }
                seatInstanceRepository.updateSeats(seatInstancesForUser);
                Intent intent = new Intent(PayActivity.this, UserHubActivity.class); // Give user back to UserHub otherwise no user?
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

}
