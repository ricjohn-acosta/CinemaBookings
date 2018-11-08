/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricjo
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class MovieSession implements Comparable<MovieSession> {

    private String movieName;
    private char rating;
    private Time sessionTime;
    private SeatReservation[][] sessionSeats;
    public static int NUM_ROWS = 3;
    public static int NUM_COLS = 5;

    @Override
    public int compareTo(MovieSession e) {  
        if (sessionTime.compareTo(e.sessionTime) == 0) {
            return movieName.compareTo(e.movieName);
        } else {
            return sessionTime.compareTo(e.sessionTime);
        }
    }

    // constructor constructs following fields whenever MovieSession is instantiated.
    public MovieSession(String movieName, char rating, Time sessionTime) {
        this.movieName = movieName;
        this.rating = rating;
        this.sessionTime = sessionTime;
        sessionSeats = new SeatReservation[NUM_ROWS][NUM_COLS];
    }
    
    // converts row letter to integer that represents the seat row
    public static int convertRowToIndex(char rowLetter) {
        return Character.getNumericValue(rowLetter) - 10;
    }

    // converts integer that represents the seat row to the row letter
    public static char convertIndexToRow(int rowIndex) {
        rowIndex += 65;
        return (char) (rowIndex);
    }
    
    // returns the movie rating
    public char getRating() {
        return this.rating;
    }

    // returns the movie name
    public String getMovieName() {
        return this.movieName;
    }

    // returns the time of a particular movie session
    public Time getSessionTime() {
        return sessionTime;
    }

    // returns seat row and column
    public SeatReservation getSeat(char row, int col) {
        return sessionSeats[convertRowToIndex(row)][col];
    }

    // checks if a seat is free or not
    public boolean isSeatAvailable(char row, int col) {
        return sessionSeats[convertRowToIndex(row)][col] == null;
    }

    // applyBookings will first check if seats from the reservation list is full
    //then it checks if the movie is rated G,M or R. If the movie is rated R
    //it counts how many people are adults, if any of the reservations are a
    //children reservation, applyBookings will return false. If the movie is 
    //rated M and there are children reservations with Adult/Elderly 
    //reservations then applyBookings will return true. Lastly, anyone can book
    //for G rated movies.
    public boolean applyBookings(List<SeatReservation> reservations) {
        int checkSeat = 0;
        int checkChild = 0;
        int checkAdult = 0;

        for (int i = 0; i <= reservations.size() - 1; i++) {
            SeatReservation temp = reservations.get(i);
            char row = temp.getRow();
            int col = temp.getCol();
            if (isSeatAvailable(row, col)) {
                checkSeat++;
            }
            if (reservations.get(i) instanceof ChildReservation) {
                checkChild++;
            }
            if (reservations.get(i) instanceof AdultReservation || reservations.get(i) instanceof ElderlyReservation) {
                checkAdult++;
            }
        }

        if (getRating() == 'R') {
            if (checkSeat == reservations.size() && checkChild == 0 && checkAdult > 0) {
                for (int i = 0; i <= reservations.size() - 1; i++) {
                    SeatReservation temp = reservations.get(i);
                    char row = temp.getRow();
                    int col = temp.getCol();
                    sessionSeats[convertRowToIndex(row)][col] = reservations.get(i);
                }
                return true;
            }
        }
        if (getRating() == 'M') {
            if ((checkSeat == reservations.size()) && (checkAdult > 0)) {
                if ((checkChild > 0) || (checkAdult == reservations.size())) {
                    for (int i = 0; i <= reservations.size() - 1; i++) {
                        SeatReservation temp = reservations.get(i);
                        char row = temp.getRow();
                        int col = temp.getCol();
                        sessionSeats[convertRowToIndex(row)][col] = reservations.get(i);
                    }
                    return true;
                }
            }
        }
        if (getRating() == 'G')
            if (checkSeat == reservations.size()) {
                for (int i = 0; i <= reservations.size() - 1; i++) {
                    SeatReservation temp = reservations.get(i);
                    char row = temp.getRow();
                    int col = temp.getCol();
                    sessionSeats[convertRowToIndex(row)][col] = reservations.get(i);
                }
            }
        return false;
    }

    // make arrayList
    public void printSeats() {
        char[][] td = new char[NUM_ROWS][NUM_COLS];

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (sessionSeats[i][j] == null) {
                    td[i][j] = '_';
                } else if (sessionSeats[i][j] instanceof AdultReservation) {
                    td[i][j] = 'A';
                } else if (sessionSeats[i][j] instanceof ChildReservation) {
                    td[i][j] = 'C';
                } else {
                    td[i][j] = 'E';
                }
            }
        }
        System.out.println(Arrays.deepToString(td).replace("],", "]\n"));
    }

    @Override
    public String toString() {
        return this.movieName + " " + "[" + this.rating + "]" + " " + this.sessionTime;
    }

    // driver class
    public static void main(String[] args) {
        // List of sessions
        Time t1 = new Time(14,30);
        Time t2 = new Time(14,25);
        Time t3 = new Time(11);
        MovieSession s1 = new MovieSession("Spiderman", 'M', t1);
        MovieSession s2 = new MovieSession("Spiderman", 'M', t2);
        MovieSession s3 = new MovieSession("Deadpool", 'R', t3);
        List<MovieSession> sessions = new ArrayList<>();
        sessions.add(s1);
        sessions.add(s2);
        sessions.add(s3);
        Collections.sort(sessions);
        System.out.println(sessions);

        // List of seat reservations
        AdultReservation adult1 = new AdultReservation('A', 1);
        AdultReservation adult2 = new AdultReservation('A', 2);
        ChildReservation child1 = new ChildReservation('A', 3);
        List<SeatReservation> reservations = new ArrayList<>();
        reservations.add(adult1);
        reservations.add(adult2);
        reservations.add(child1);

        // Group of seat reservations decide to watch spiderman 
        s1.applyBookings(reservations);
        s1.printSeats();
        s2.printSeats();
        s3.printSeats();

    }
}
