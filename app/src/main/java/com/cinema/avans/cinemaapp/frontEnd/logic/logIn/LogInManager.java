package com.cinema.avans.cinemaapp.frontEnd.logic.logIn;

import com.cinema.avans.cinemaapp.frontEnd.dataAcces.repositories.RepositoryFactory;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.LogIn;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

/**
 * Created by JanBelterman on 29 March 2018
 */

public class LogInManager {

    private RepositoryFactory repositoryFactory;
    private LogInActivity logInActivity;

    public LogInManager(LogInActivity logInActivity, RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        this.logInActivity = logInActivity;

    }

    public void logIn(String userName, String password) {

        LogIn logIn = repositoryFactory.getLogInRepository().getLogIn(userName);

        if (logIn.getId() == 2) {

            if (logIn.getUserId().equals(userName) && logIn.getPassword().equals(password)) {
                logInActivity.managerLogIn((Manager) logIn);
                return;
            }

        } if (logIn.getId() == 1) {

            if (logIn.getUserId().equals(userName) && logIn.getPassword().equals(password)) {
                logInActivity.userLogIn((User) logIn);
                return;
            }

        }

        logInActivity.showError("Invalid credentials!");

    }

}
