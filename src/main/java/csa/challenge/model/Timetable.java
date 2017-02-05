package csa.challenge.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class Timetable {

    public List<Connection> connections = new ArrayList<>();

    // Timetable constructor: reads all the connections from resource zipped file
    public Timetable(final String fileName) {
        System.out.print("Loading bench datas...");
        final Instant start = Instant.now();
        try (final InputStream fis = getClass().getResourceAsStream(fileName);
             final GZIPInputStream gzis = new GZIPInputStream(fis);
             final InputStreamReader isr = new InputStreamReader(gzis);
             final BufferedReader br = new BufferedReader(isr)) {
            //read resources and build timetable
            br.lines().parallel().forEachOrdered(line -> connections.add(new Connection(line)));
        } catch (final IOException exception) {
            System.out.println("Something went wrong while reading the data: " + exception.getMessage());
        }
        final Duration duration = Duration.between(start, Instant.now());
        System.out.println("done (" + duration.toString().replace("PT", "") + ")");
    }

    public final List<Connection> getConnections() {
        return connections;
    }

}