package csa.challenge;

import csa.challenge.model.Connection;
import csa.challenge.model.Timetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSA {

    private static final int MAX_STATIONS  = 100000;

    private Timetable timetable;

    private Connection in_connection[];

    private int earliest_arrival[];

    public CSA(final Timetable timetable) {
        this.timetable = timetable;
    }

    private void main_loop(int arrival_station) {
        int earliest = Integer.MAX_VALUE;

        for (final Connection connection: timetable.connections) {
            if (connection.departureTimestamp >= earliest_arrival[connection.departureStation] + connection.departureChangeTimeInMs &&
                    connection.arrivalTimestamp < earliest_arrival[connection.arrivalStation]) {
                earliest_arrival[connection.arrivalStation] = connection.arrivalTimestamp;
                in_connection[connection.arrivalStation] = connection;

                if(connection.arrivalStation == arrival_station) {
                    earliest = Math.min(earliest, connection.arrivalTimestamp);
                }
            } else if(connection.arrivalTimestamp > earliest) {
                return;
            }
        }
    }

    private void print_result(int arrival_station) {
        if(in_connection[arrival_station] == null) {
            System.out.println("NO_SOLUTION");
        } else {
            List<Connection> route = new ArrayList<>();
            // We have to rebuild the route from the arrival station 
            Connection last_connection = in_connection[arrival_station];
            while (last_connection != null) {
                route.add(last_connection);
                last_connection = in_connection[last_connection.getDepartureStation()];
            }

            // And now print it out in the right direction
            Collections.reverse(route);
            for (Connection connection : route) {
                System.out.println(connection.getDepartureStation() + " " + connection.getArrivalStation() + " " +
                        connection.getDepartureTimestamp() + " " + connection.getArrivalTimestamp());
            }
        }
        System.out.println("");
        System.out.flush();
    }

    private void compute(int departure_station, int arrival_station, int departure_time, int change_time) {
        in_connection = new Connection[MAX_STATIONS];
        earliest_arrival = new int[MAX_STATIONS];
        for(int i = 0; i < MAX_STATIONS; ++i) {
            in_connection[i] = null;
            earliest_arrival[i] = Integer.MAX_VALUE;
        }
        earliest_arrival[departure_station] = departure_time;

        if (departure_station <= MAX_STATIONS && arrival_station <= MAX_STATIONS) {
            main_loop(arrival_station);
        }
        //print_result(arrival_station);
    }

    public void compute(final String line){
        final String[] tokens = line.split(" ");
        compute(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[2]));
    }

}