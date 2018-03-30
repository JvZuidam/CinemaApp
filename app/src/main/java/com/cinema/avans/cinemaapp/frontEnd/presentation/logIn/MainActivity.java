package com.cinema.avans.cinemaapp.frontEnd.presentation.logIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.backEnd.DatabaseManager;
import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInActivity;
import com.cinema.avans.cinemaapp.frontEnd.logic.logIn.LogInManager;
import com.cinema.avans.cinemaapp.frontEnd.presentation.ActivityMovieList;
import com.cinema.avans.cinemaapp.frontEnd.presentation.register.RegisterActivity;

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
                ,new RepositoryFactory(
                        new DatabaseManager(
                                getApplicationContext()
                                ,"Cinema"
                                ,null
                                ,1)));

        // Setup views
        usernameInput = findViewById(R.id.registerUsernameInput);
        passwordInput = findViewById(R.id.registerPasswordConfirmInput);

        // Setup buttons
        Button logInButton = findViewById(R.id.registerRegisterButton);
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
                finish(); // Finish this, so user cant go back

            }
        });

    }

    @Override
    public void managerLogIn(Manager manager) {

        // Go to manager screen

    }

    @Override
    public void userLogIn(User user) {

        // Go to movie list screen for user
        Intent intent = new Intent(MainActivity.this, ActivityMovieList.class);
        intent.putExtra("USER", user);
        startActivity(intent);
        finish(); // Finish this, so user cant go back

    }

    public void showError(String error) {

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();

    }

}
