package com.cinema.avans.cinemaapp.backEnd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cinema.avans.cinemaapp.frontEnd.domain.Movie;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Showing;
import com.cinema.avans.cinemaapp.frontEnd.domain.Ticket;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Hall;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.HallInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.Seat;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRow;
import com.cinema.avans.cinemaapp.frontEnd.domain.cinema.SeatRowInstance;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.LogIn;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.Manager;
import com.cinema.avans.cinemaapp.frontEnd.domain.login.User;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class DatabaseManager extends SQLiteOpenHelper {

    //////////////////////////////////////////////////////////////////////////////////////////////
    // DATABASE NAMES
    //////////////////////////////////////////////////////////////////////////////////////////////

    // Database name
    private static final String DATABASE_NAME = "Cinema";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Hall Table
    private static final String TABLE_HALL = "Hall";
    // -------------------------------------------------------------------------------------------
    private static final String HALL_COLUMN_HALL_ID = "HallId"; //PK

    // SeatRowInstance table
    private static final String TABLE_SEAT_ROW = "SeatRow";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_ROW_COLUMN_ROW_ID = "RowId"; //PK
    private static final String SEAT_ROW_COLUMN_HALL_ID = "HallId"; // Hall this seat row is part off
    private static final String SEAT_ROW_COLUMN_ROW_NR = "RowNr"; // Row number in hall

    // SeatInstance table
    private static final String TABLE_SEAT = "Seat";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_COLUMN_SEAT_ID = "SeatId"; //PK
    private static final String SEAT_COLUMN_ROW_ID = "RowId"; // Row this seat is part of (also stores which hall!)
    private static final String SEAT_COLUMN_SEAT_NR = "SeatNr"; // SeatInstance number in row
    private static final String SEAT_COLUMN_SEAT_VALUE = "SeatValue"; // Value BAD, MODERATE, OK, GOOD, PERFECT

    // HallInstance table
    private static final String TABLE_HALL_INSTANCE = "HallInstance";
    // -------------------------------------------------------------------------------------------
    private static final String HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID = "HallInstanceId"; //PK
    private static final String HALL_INSTANCE_COLUMN_HALL_ID = "HallId"; // Hall this instance originated from

    // SeatRowInstance instance table
    private static final String TABLE_SEAT_ROW_INSTANCE = "SeatRowInstance";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID = "RowInstanceId"; //PK
    private static final String SEAT_ROW_INSTANCE_COLUMN_ROW_ID = "RowId"; // Row this instance originated from
    private static final String SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID = "HallInstanceId"; // HallInstance this row instance is part off

    // SeatInstanceTable
    private static final String TABLE_SEAT_INSTANCE = "SeatInstance";
    // -------------------------------------------------------------------------------------------
    private static final String SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID = "SeatInstanceId"; //PK
    private static final String SEAT_INSTANCE_COLUMN_SEAT_ID = "SeatId"; // SeatInstance this instance originated from
    private static final String SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID = "SeatRowInstanceId";
    private static final String SEAT_INSTANCE_COLUMN_STATUS = "Status"; // Status, RESERVED, AVAILABLE, GAP(no seat, just room)

    // Movie table
    private static final String TABLE_MOVIE = "Movie";
    // -------------------------------------------------------------------------------------------
    private static final String MOVIE_COLUMN_MOVIE_ID = "MovieId";
    private static final String MOVIE_COLUMN_TITLE = "Title";
    private static final String MOVIE_COLUMN_DESCRIPTION = "Description";
    private static final String MOVIE_COLUMN_IMAGE_URL = "ImageUr";

    // Showing table
    private static final String TABLE_SHOWING = "Showing";
    // -------------------------------------------------------------------------------------------
    private static final String SHOWING_COLUMN_SHOWING_ID = "ShowingId"; // PK
    private static final String SHOWING_COLUMN_HALL_INSTANCE_ID = "HallId"; // Hall this showing is in
    private static final String SHOWING_COLUMN_MOVIE_ID = "MovieId"; // Movie this showing shows
    private static final String SHOWING_COLUMN_DATE = "Date"; // Date of the showing

    // Ticket table
    private static final String TABLE_TICKET = "Ticket";
    // -------------------------------------------------------------------------------------------
    private static final String TICKET_COLUMN_TICKET_ID = "TicketId";
    private static final String TICKET_COLUMN_SHOWING_ID = "ShowingId";
    private static final String TICKET_COLUMN_SEAT_INSTANCE_ID = "SeatInstance";

    // LogIn table
    private static final String TABLE_LOG_IN = "LogIn";
    // -------------------------------------------------------------------------------------------
    private static final String LOG_IN_COLUMN_USERNAME = "Username";
    private static final String LOG_IN_COLUMN_PASSWORD = "Password";

    // User table
    private static final String TABLE_USER = "User";
    // -------------------------------------------------------------------------------------------
    private static final String USER_COLUMN_USERNAME = "Username";

    // Manager table
    private static final String TABLE_MANAGER = "Manager";
    // -------------------------------------------------------------------------------------------
    private static final String MANAGER_COLUMN_USERNAME = "Username";

    //////////////////////////////////////////////////////////////////////////////////////////////
    // CREATE QUERIES
    //////////////////////////////////////////////////////////////////////////////////////////////

    private final String CREATE_TABLE_HALL =
            "CREATE TABLE " + TABLE_HALL + " (" + "\n"
                    + HALL_COLUMN_HALL_ID + " INTEGER PRIMARY KEY" + ");";

    private final String CREATE_TABLE_SEAT_ROW =
            "CREATE TABLE " + TABLE_SEAT_ROW + " (" + "\n"
                    + SEAT_ROW_COLUMN_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_ROW_COLUMN_HALL_ID + " INTEGER," + "\n"
                    + SEAT_ROW_COLUMN_ROW_NR + " INTEGER" + ");";

    private final String CREATE_TABLE_SEAT =
            "CREATE TABLE " + TABLE_SEAT + " (" + "\n"
                    + SEAT_COLUMN_SEAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_COLUMN_ROW_ID + " INTEGER," + "\n"
                    + SEAT_COLUMN_SEAT_NR + " INTEGER," + "\n"
                    + SEAT_COLUMN_SEAT_VALUE + " INTEGER" + ");";

    private final String CREATE_TABLE_HALL_INSTANCE =
            "CREATE TABLE " + TABLE_HALL_INSTANCE + " (" + "\n"
                    + HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + HALL_INSTANCE_COLUMN_HALL_ID + " INTEGER" + ");";

    private final String CREATE_TABLE_SEAT_ROW_INSTANCE =
            "CREATE TABLE " + TABLE_SEAT_ROW_INSTANCE + " (" + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_ROW_ID + " INTEGER," + "\n"
                    + SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID + " INTEGER" + ");";

    private final String CREATE_TABLE_SEAT_INSTANCE =
            "CREATE TABLE " + TABLE_SEAT_INSTANCE + " (" + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_ID + " INTEGER," + "\n"
                    + SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID + " INTEGER," + "\n"
                    + SEAT_INSTANCE_COLUMN_STATUS + " INTEGER" + ");";

    private final String CREATE_TABLE_MOVIE =
            "CREATE TABLE " + TABLE_MOVIE + " (" + "\n"
                    + MOVIE_COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + MOVIE_COLUMN_TITLE + " TEXT," + "\n"
                    + MOVIE_COLUMN_DESCRIPTION + " TEXT," + "\n"
                    + MOVIE_COLUMN_IMAGE_URL + " TEXT" + ");";

    private final String CREATE_TABLE_SHOWING =
            "CREATE TABLE " + TABLE_SHOWING + " (" + "\n"
            + SHOWING_COLUMN_SHOWING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
            + SHOWING_COLUMN_HALL_INSTANCE_ID + " INTEGER," + "\n"
            + SHOWING_COLUMN_MOVIE_ID + " INTEGER," + "\n"
            + SHOWING_COLUMN_DATE + " TEXT" + ");"; // YYYY-MM-DD-HH-MM

    private static final String CREATE_TABLE_TICKET =
            "CREATE TABLE " + TABLE_TICKET + " (" + "\n"
                    + TICKET_COLUMN_TICKET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
                    + TICKET_COLUMN_SHOWING_ID + " INTEGER," + "\n"
                    + TICKET_COLUMN_SEAT_INSTANCE_ID + " INTEGER" + ");";

    private static final String CREATE_TABLE_LOG_IN =
            "CREATE TABLE " + TABLE_LOG_IN + " (" + "\n"
                    + LOG_IN_COLUMN_USERNAME + " TEXT PRIMARY KEY," + "\n"
                    + LOG_IN_COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_USER + " (" + "\n"
                    + USER_COLUMN_USERNAME + " TEXT PRIMARY KEY" + ")";

    private static final String CREATE_TABLE_MANAGER =
            "CREATE TABLE " + TABLE_MANAGER + " (" + "\n"
                    + MANAGER_COLUMN_USERNAME + " TEXT PRIMARY KEY" + ")";

    //////////////////////////////////////////////////////////////////////////////////////////////
    // Methods
    //////////////////////////////////////////////////////////////////////////////////////////////

    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Creating tables
        sqLiteDatabase.execSQL(CREATE_TABLE_HALL); // Create
        Log.i("Database", CREATE_TABLE_HALL); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_ROW); // Create
        Log.i("Database", CREATE_TABLE_SEAT_ROW); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT); // Create
        Log.i("Database", CREATE_TABLE_SEAT); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_HALL_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_HALL_INSTANCE); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_ROW_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_SEAT_ROW_INSTANCE); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_SEAT_INSTANCE); // Create
        Log.i("Database", CREATE_TABLE_SEAT_INSTANCE); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE); // Create
        Log.i("Database", CREATE_TABLE_MOVIE); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_SHOWING); // Create
        Log.i("Database", CREATE_TABLE_SHOWING); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_TICKET); // Create
        Log.i("Database", CREATE_TABLE_TICKET); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_LOG_IN); // Create
        Log.i("Database", CREATE_TABLE_LOG_IN); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_USER); // Create
        Log.i("Database", CREATE_TABLE_USER); // Print
        sqLiteDatabase.execSQL(CREATE_TABLE_MANAGER); // Create
        Log.i("Database", CREATE_TABLE_MANAGER); // Print

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_ROW);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HALL_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_ROW_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEAT_INSTANCE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWING);

        // create new tables
        onCreate(sqLiteDatabase);

    }

    public void createHall(Hall hall) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HALL_COLUMN_HALL_ID, hall.getHallId());

        database.insert(TABLE_HALL, null, values);

    }
    public Hall getHall(int hallId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_HALL + "\n"
                        + "WHERE " + HALL_COLUMN_HALL_ID + " = " + hallId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            Hall hall = new Hall();
            hall.setHallId(cursor.getInt(cursor.getColumnIndex(HALL_COLUMN_HALL_ID)));

            return hall;

        }

        return null;

    }

    public void createSeatRow(SeatRow seatRow) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SEAT_ROW_COLUMN_HALL_ID, seatRow.getHall().getHallId());
        values.put(SEAT_ROW_COLUMN_ROW_ID, seatRow.getRowId());
        values.put(SEAT_ROW_COLUMN_ROW_NR, seatRow.getRowNr());

        database.insert(TABLE_SEAT_ROW, null, values);

    }
    public ArrayList<SeatRow> getSeatRows(int hallId) {

        ArrayList<SeatRow> seatRows = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW + "\n"
                        + "WHERE " + SEAT_ROW_COLUMN_HALL_ID + " = " + hallId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                SeatRow seatRow = new SeatRow();
                seatRow.setHall(getHall(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_HALL_ID))));
                seatRow.setRowId(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_ID)));
                seatRow.setRowNr(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_NR)));

                seatRows.add(seatRow);

                cursor.moveToNext();

            }

        }

        return seatRows;

    }
    public int getSeatRow(int rowId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW + "\n"
                        + "WHERE " + SEAT_ROW_COLUMN_ROW_ID + " = " + rowId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            SeatRow seatRow = new SeatRow();
            seatRow.setHall(getHall(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_HALL_ID))));
            seatRow.setRowId(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_ID)));
            seatRow.setRowNr(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_COLUMN_ROW_NR)));

            return seatRow.getRowNr();

        }

        return 0;

    }

    public void createSeat(Seat seat) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SEAT_COLUMN_ROW_ID, seat.getSeatRow().getRowId());
        values.put(SEAT_COLUMN_SEAT_NR, seat.getSeatNr());
        values.put(SEAT_COLUMN_SEAT_VALUE, seat.getSeatValueInt());

        database.insert(TABLE_SEAT, null, values);

    }
    public ArrayList<Seat> getSeats(int rowId) {

        ArrayList<Seat> seats = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT + "\n"
                        + "WHERE " + SEAT_COLUMN_ROW_ID + " = " + rowId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                Seat seat = new Seat();
                seat.setSeatId(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_ID)));
                seat.setSeatNr(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_NR)));
                seat.setValue(cursor.getInt(cursor.getColumnIndex(SEAT_COLUMN_SEAT_VALUE)));

                seats.add(seat);

            }

        }

        return seats;

    }

    public void createHallInstance(HallInstance hallInstance) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HALL_INSTANCE_COLUMN_HALL_ID, hallInstance.getHallId());

        database.insert(TABLE_HALL_INSTANCE, null, values);

    }
    public HallInstance getHallInstance(int hallInstanceId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_HALL_INSTANCE + "\n"
                + "WHERE " + HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID + " = " + hallInstanceId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            HallInstance hallInstance = new HallInstance();
            hallInstance.setHallInstanceId(cursor.getInt(cursor.getColumnIndex(HALL_INSTANCE_COLUMN_HALL_INSTANCE_ID)));
            hallInstance.setHallId(cursor.getInt(cursor.getColumnIndex(HALL_INSTANCE_COLUMN_HALL_ID)));

            return hallInstance;

        }

        return null;

    }

    public void createSeatRowInstance(SeatRowInstance seatRowInstance) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID, seatRowInstance.getHallInstance().getHallInstanceId());
        values.put(SEAT_ROW_INSTANCE_COLUMN_ROW_ID, seatRowInstance.getSeatRowId());

        database.insert(TABLE_SEAT_ROW_INSTANCE, null, values);

    }
    public ArrayList<SeatRowInstance> getSeatRowInstances(HallInstance hallInstance) {

        ArrayList<SeatRowInstance> seatRowInstances = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_ROW_INSTANCE + "\n"
                        + "WHERE " + SEAT_ROW_INSTANCE_COLUMN_HALL_INSTANCE_ID + " = " + hallInstance.getHallInstanceId();

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                SeatRowInstance seatRowInstance = new SeatRowInstance();
                seatRowInstance.setSeatRowInstanceId(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_INSTANCE_COLUMN_ROW_INSTANCE_ID)));
                seatRowInstance.setSeatRowId(cursor.getInt(cursor.getColumnIndex(SEAT_ROW_INSTANCE_COLUMN_ROW_ID)));

                seatRowInstances.add(seatRowInstance);

                cursor.moveToNext();

            }

        }

        return seatRowInstances;

    }

    public void createSeatInstance(SeatInstance seatInstance) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SEAT_INSTANCE_COLUMN_SEAT_ID, seatInstance.getSeatId());
        values.put(SEAT_INSTANCE_COLUMN_STATUS, seatInstance.getStatusInt()); // 1 = Available, 2 = Reserved, 3 = Gap
        values.put(SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID, seatInstance.getSeatInstanceId());

        database.insert(TABLE_SEAT_INSTANCE, null, values);

    }
    public ArrayList<SeatInstance> getSeatInstances(SeatRowInstance seatRowInstance) {

        ArrayList<SeatInstance> seatInstances = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_INSTANCE + "\n"
                        + "WHERE " + SEAT_INSTANCE_COLUMN_SEAT_ROW_INSTANCE_ID + " = " + seatRowInstance.getSeatRowInstanceId();

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                SeatInstance seatInstance = new SeatInstance();
                seatInstance.setStatus(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_STATUS)));
                seatInstance.setSeatId(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_ID)));
                seatInstance.setSeatInstanceId(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID)));

                seatInstances.add(seatInstance);

                cursor.moveToNext();

            }

        }

        return seatInstances;

    }
    public SeatInstance getSeatInstance(int seatInstanceId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SEAT_INSTANCE + "\n"
                        + "WHERE " + SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID + " = " + seatInstanceId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            SeatInstance seatInstance = new SeatInstance();
            seatInstance.setStatus(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_STATUS)));
            seatInstance.setSeatId(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_ID)));
            seatInstance.setSeatInstanceId(cursor.getInt(cursor.getColumnIndex(SEAT_INSTANCE_COLUMN_SEAT_INSTANCE_ID)));

            return seatInstance;

        }

        return null;

    }

    public void createMovie(Movie movie) {

        SQLiteDatabase database = getWritableDatabase();

        Log.i("Database", "Adding movie: " + movie);

        ContentValues values = new ContentValues();
        values.put(MOVIE_COLUMN_TITLE, movie.getTitle());
        values.put(MOVIE_COLUMN_DESCRIPTION, movie.getDescription());
        values.put(MOVIE_COLUMN_IMAGE_URL, movie.getImageUrl());

        database.insert(TABLE_MOVIE, null, values);

    }
    public Movie getMovie(int movieId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_MOVIE + "\n"
                        + "WHERE " + MOVIE_COLUMN_MOVIE_ID + " = " + movieId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            Movie movie = new Movie();
            movie.setMovieId(cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DESCRIPTION)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_IMAGE_URL)));

            return movie;

        }

        return null;

    }
    public ArrayList<Movie> getAllMovies() {

        ArrayList<Movie> movies = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                + "FROM " + TABLE_MOVIE;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                Movie movie = new Movie();
                movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_IMAGE_URL)));
                movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DESCRIPTION)));
                movie.setMovieId(cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_TITLE)));

                movies.add(movie);

                cursor.moveToNext();

            }

        }

        return movies;

    }

    public void createShowing(Showing showing) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SHOWING_COLUMN_HALL_INSTANCE_ID, showing.getHallInstance().getHallInstanceId());
        values.put(SHOWING_COLUMN_MOVIE_ID, showing.getMovie().getMovieId());
        values.put(SHOWING_COLUMN_DATE, showing.getDate().getDate());

        database.insert(TABLE_SHOWING, null, values);

    }
    public ArrayList<Showing> getShowings(Movie movie) {

        ArrayList<Showing> showings = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SHOWING + "\n"
                        + "WHERE " + SHOWING_COLUMN_MOVIE_ID + " = " + movie.getMovieId();

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isLast()) {

                Showing showing = new Showing();
                showing.setDate(cursor.getString(cursor.getColumnIndex(SHOWING_COLUMN_DATE)));
                showing.setShowingId(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_SHOWING_ID)));

                showings.add(showing);

                cursor.moveToNext();

            }

        }

        return showings;

    }
    public Showing getShowing(int showingId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_SHOWING + "\n"
                        + "WHERE " + SHOWING_COLUMN_SHOWING_ID + " = " + showingId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            Showing showing = new Showing();
            showing.setDate(cursor.getString(cursor.getColumnIndex(SHOWING_COLUMN_DATE)));
            showing.setShowingId(cursor.getInt(cursor.getColumnIndex(SHOWING_COLUMN_SHOWING_ID)));

            return showing;

        }

        return null;

    }

    public void createTicket(Ticket ticket) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TICKET_COLUMN_SHOWING_ID, ticket.getShowing().getShowingId());
        values.put(TICKET_COLUMN_SEAT_INSTANCE_ID, ticket.getSeatInstance().getSeatInstanceId());

        database.insert(TABLE_TICKET, null, values);

    }
    public Ticket getTicket(int ticketId) {

        SQLiteDatabase database = getReadableDatabase();

        String query =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_TICKET + "\n"
                        + "WHERE " + TICKET_COLUMN_TICKET_ID + " = " + ticketId;

        Log.i("Database", query);

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();

            Ticket ticket = new Ticket();
            ticket.setTicketId(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_TICKET_ID)));
            ticket.setShowing(getShowing(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SHOWING_ID))));
            ticket.setSeatInstance(getSeatInstance(cursor.getInt(cursor.getColumnIndex(TICKET_COLUMN_SEAT_INSTANCE_ID))));

            return ticket;

        }

        return null;

    }

    public void createUser(User user) {

        Log.i("Database", "Inserting user: " + user);

        SQLiteDatabase database = getWritableDatabase();

        ContentValues logInValues = new ContentValues();
        logInValues.put(LOG_IN_COLUMN_USERNAME, user.getUserId());
        logInValues.put(LOG_IN_COLUMN_PASSWORD, user.getPassword());

        database.insert(TABLE_LOG_IN, null, logInValues);

        ContentValues userValues = new ContentValues();
        userValues.put(LOG_IN_COLUMN_USERNAME, user.getUserId());

        database.insert(TABLE_USER, null, userValues);

    }
    public User getUser(String username) {

        User user = new User();

        SQLiteDatabase database = getReadableDatabase();

        String logInQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_LOG_IN + "\n"
                        + "WHERE " + LOG_IN_COLUMN_USERNAME + " = '" + username + "'";

        Log.i("Database", logInQuery);

        Cursor logInCursor = database.rawQuery(logInQuery, null);

        if (logInCursor.moveToFirst()) {

            logInCursor.moveToFirst();

            user.setUserId(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_USERNAME)));
            user.setPassword(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_PASSWORD)));

        }

        String userQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_USER + "\n"
                        + "WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";

        Log.i("Database", userQuery);

        Cursor userCursor = database.rawQuery(userQuery, null);

        if (logInCursor.moveToFirst()) {

            // Used if user gets more attributes

        }

        return user;

    }

    public void createManager(Manager manager) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues logInValues = new ContentValues();
        logInValues.put(LOG_IN_COLUMN_USERNAME, manager.getUserId());
        logInValues.put(LOG_IN_COLUMN_PASSWORD, manager.getPassword());

        database.insert(TABLE_LOG_IN, null, logInValues);

        ContentValues userValues = new ContentValues();
        userValues.put(LOG_IN_COLUMN_USERNAME, manager.getUserId());

        database.insert(TABLE_USER, null, userValues);

    }
    public Manager getManager(String username) {

        Manager manager = new Manager();

        SQLiteDatabase database = getReadableDatabase();

        String logInQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_LOG_IN + "\n"
                        + "WHERE " + LOG_IN_COLUMN_USERNAME + " = '" + username + "'";

        Log.i("Database", logInQuery);

        Cursor logInCursor = database.rawQuery(logInQuery, null);

        if (logInCursor.moveToFirst()) {

            logInCursor.moveToFirst();

            manager.setUserId(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_USERNAME)));
            manager.setPassword(logInCursor.getString(logInCursor.getColumnIndex(LOG_IN_COLUMN_PASSWORD)));

        }

        String managerQuery =
                "SELECT *" + "\n"
                        + "FROM " + TABLE_MANAGER + "\n"
                        + "WHERE " + MANAGER_COLUMN_USERNAME + " = '" + username + "'";

        Log.i("Database", managerQuery);

        Cursor userCursor = database.rawQuery(managerQuery, null);

        if (logInCursor.moveToFirst()) {

            // Used if manager gets more attributes

        }

        return manager;

    }

}