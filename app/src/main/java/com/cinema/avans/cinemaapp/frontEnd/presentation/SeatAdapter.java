package com.cinema.avans.cinemaapp.frontEnd.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cinema.avans.cinemaapp.R;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.CinemaHall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatStatus;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class SeatAdapter extends BaseAdapter {

    private Context context;
    private CinemaHall cinemaHall;

    // Constructor, sets the context and hallItem
    public SeatAdapter(Context context
            ,CinemaHall cinemaHall) {

        this.context = context;
        this.cinemaHall = cinemaHall;

    }

    // Method that returns the amount of seats of the complete hall
    public int getCount() {

        int count = 0;

        for (SeatRow seatRow : cinemaHall.getRows()) {

            for (Seat seat : seatRow.getSeats()) {
                count ++;

            }

        }

        return count;

    }

    // Method that returns a Seat item for a given position
    // - Stats at row 1 and seat 1 (1.1) or the top, most left seat
    // - Ends at row (x.x) or the bottom most right seat
    public Seat getItem(int position) {

        Seat seatItem = null;
        int index = 0;

        for (SeatRow seatRow : cinemaHall.getRows()) {

            for (Seat seat : seatRow.getSeats()) {

                if (index == position) {
                    seatItem = seat;

                }

                index ++;

            }
        }

        return seatItem;

    }

    // Creates a new gridView item for a given seat position
    public View getView(int position, View convertView, ViewGroup parent) {

        // Create imageView
        ImageView imageView;

        // ImageView to convertView
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);

            imageView.setLayoutParams(new ViewGroup.LayoutParams(40, 40));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);

        } else {
            imageView = (ImageView) convertView;

        }

        // Adjust color
        SeatStatus seatStatus = getItem(position).getStatus();
        if (seatStatus == SeatStatus.AVAILABLE) {
            imageView.setImageResource(R.color.Available);

        } else if (seatStatus == SeatStatus.RESERVED) {
            imageView.setImageResource(R.color.Reserved);

        } else if (seatStatus == SeatStatus.SELECTED) {
            imageView.setImageResource(R.color.Selected);

        } else {
            imageView.setImageResource(R.color.Gap);

        }

        // Return the gridView item
        return imageView;

    }

    // Inherited method
    public long getItemId(int position) {
        return 0;

    }

}
