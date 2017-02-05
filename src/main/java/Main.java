import csa.challenge.CSA;
import csa.challenge.model.Timetable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  //use bench_data_48h.gz from resources
  private static final String benchDataPath = "/bench_data_48h.gz";

  //use stations from resources
  private static final String topStationsPath = "/stations";

  public static void main(final String[] args) {
    //Build Timetable
    final Timetable timetable = new Timetable(benchDataPath);
    final CSA csa = new CSA(timetable);
    //Build bench
    System.out.print("Loading top stations...");
    final Instant startLS = Instant.now();
    try (final InputStream fis = Main.class.getResourceAsStream(topStationsPath);
         final InputStreamReader isr = new InputStreamReader(fis);
         final BufferedReader br = new BufferedReader(isr)
    ) {
      final List<String> lines = br.lines().collect(Collectors.toCollection(ArrayList::new));
      final Duration durationLS = Duration.between(startLS, Instant.now());
      System.out.println("done (" + durationLS.toString().replace("PT", "") + ")");
      System.out.print("Starting bench...");
      final Instant start = Instant.now();
      for(final String line : lines){
        csa.compute(line);
      }
      final Duration duration = Duration.between(start, Instant.now());
      System.out.println("done");
      System.out.println("Total time: " + duration.toString().replace("PT", ""));
      System.out.println("Average time per search: " + duration.dividedBy(lines.size()).toString().replace("PT", ""));
    } catch (final IOException ioe){
      System.out.println("Error accessing Top Stations datas :" + ioe.fillInStackTrace());
    }
  }

}
