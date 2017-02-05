package csa.challenge.model;

public class Connection {

    public int departureStation;

    public int arrivalStation;

    public int departureTimestamp;

    public int arrivalTimestamp;

    public int departureChangeTimeInMs;

    // Connection constructor
    Connection(final String line) {
        final String[] tokens = line.trim().split(" ");

        setDepartureStation(Integer.parseInt(tokens[0]));
        setArrivalStation(Integer.parseInt(tokens[1]));
        setDepartureTimestamp(Integer.parseInt(tokens[2]));
        setArrivalTimestamp(Integer.parseInt(tokens[3]));
    }

    public final int getDepartureStation() {
        return departureStation;
    }

    private void setDepartureStation(final int departureStation) {
        this.departureStation = departureStation;
    }

    public final int getArrivalStation() {
        return arrivalStation;
    }

    private void setArrivalStation(final int arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public final int getDepartureTimestamp() {
        return departureTimestamp;
    }

    private void setDepartureTimestamp(final int departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public final int getArrivalTimestamp() {
        return arrivalTimestamp;
    }

    private void setArrivalTimestamp(final int arrivalTimestamp) {
        this.arrivalTimestamp = arrivalTimestamp;
    }
}