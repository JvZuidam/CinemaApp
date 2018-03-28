package com.cinema.avans.cinemaapp.frontEnd.domain;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class Date {

    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;

    // SETTERS
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    // GETTERS
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public int getHours() {
        return hours;
    }
    public int getMinutes() {
        return minutes;
    }

}
