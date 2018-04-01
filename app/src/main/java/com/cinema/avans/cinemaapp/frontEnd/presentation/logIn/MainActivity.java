package com.cinema.avans.cinemaapp.frontEnd.presentation.logIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.ManagerRepository;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.UserRepository;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatValue;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInActivity;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInManager;
import com.cinema.avans.cinemaapp.frontEnd.presentation.user.MovieListActivity;
import com.cinema.avans.cinemaapp.frontEnd.presentation.manager.ManagerHubActivity;
import com.cinema.avans.cinemaapp.frontEnd.presentation.register.RegisterActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LogInActivity {

    // Manager
    private LogInManager logInManager;

    // Views
    private TextView usernameInput;
    private TextView passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Setup manager
        this.logInManager = new LogInManager(
                this
                ,new RepositoryFactory(getApplicationContext()));

        insertFakeBackendDataToDatabase();

        setUpButtons();

        // Setup views
        usernameInput = findViewById(R.id.registerUsernameInput);
        passwordInput = findViewById(R.id.registerPasswordConfirmInput);

    }

    private void insertFakeBackendDataToDatabase() {

        Log.i("LogInActivity", "Inserting fake backend data into database");

        RepositoryFactory repositoryFactory = new RepositoryFactory(getApplicationContext());

        // Backend data
        Manager manager = new Manager();
        manager.setUserId("Manager");
        manager.setPassword("12345");
        repositoryFactory.getManagerRepository().createManager(manager);
        User user = new User();
        user.setUserId("User");
        user.setPassword("12345");
        repositoryFactory.getUserRepository().createUser(user);

        Hall hall = new Hall();
        hall.setHallId(1);
        ArrayList<SeatRow> seatRows = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {

            SeatRow seatRow = new SeatRow();
            seatRow.setRowNr(i + 1);
            seatRow.setHall(hall);
            ArrayList<Seat> seats = new ArrayList<>();
            for (int j = 0; j <= 12; j++ ) {

                Seat seat = new Seat();
                seat.setSeatRow(seatRow);
                seat.setSeatValue(SeatValue.OK);
                seat.setSeatNr(j + 1);
                seats.add(seat);

            }
            seatRow.setSeats(seats);

        }

        repositoryFactory.getHallRepository().createEntireHall(hall);


    }

    private void setUpButtons() {

        // Setup buttons
        Button logInButton = findViewById(R.id.loginButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());

                if (username.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Fill in username first", Toast.LENGTH_LONG).show();
                    return;
                } else if (password.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Fill in password first", Toast.LENGTH_LONG).show();
                    return;
                }

                // Try logging in, logInManager calls this class back with appropriate method
                logInManager.logIn(username, password);

            }
        });

        TextView registerText = findViewById(R.id.longInRegisterLink);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start register activity (pass username to auto fill for user)
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("USERNAME", usernameInput.getText().toString());
                startActivity(intent);

            }
        });

    }

    @Override
    public void managerLogIn(Manager manager) {

        // Go to manager screen
        Intent intent = new Intent(MainActivity.this, ManagerHubActivity.class);
        startActivity(intent);

    }

    @Override
    public void userLogIn(User user) {

        // Go to movie list screen for user
        Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);

    }

    public void showError(String error) {

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();

    }

}
