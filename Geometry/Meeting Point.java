import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
  public static BufferedReader in;

  public static void main(String[] args) throws IOException {
    in = new BufferedReader(new InputStreamReader(System.in));
    Coordinate[] coordinates = readCoordinates();
    long accumulatedX = 0;
    long accumulatedY = 0;
    for (Coordinate coordinate : coordinates) {
      accumulatedX += coordinate.x;
      accumulatedY += coordinate.y;
    }
    final Coordinate average = new Coordinate(accumulatedX / coordinates.length, accumulatedY / coordinates.length);
    
    Arrays.sort(coordinates, new Comparator<Coordinate>() {
      @Override
      public int compare(Coordinate arg0, Coordinate arg1) {
        long value = calculateTravelTime(arg0, average) - calculateTravelTime(arg1, average);
        if (value == 0)
          return 0;
        else
         return (int)(value / Math.abs(value));
      }
    });
    
    long minTime = Long.MAX_VALUE;
    for (int i = 0; i <= Math.min(coordinates.length/100, 10); i++) {
      long time = 0;
      for (int j = 0; time <= minTime && j < coordinates.length; j++) {
        time += calculateTravelTime(coordinates[j], coordinates[i]);
      }
      minTime = Math.min(minTime, time);
    }
    System.out.println(minTime);
  }

  public static long calculateTravelTime(Coordinate source, Coordinate destination) {
    return Math.max(Math.abs(destination.x-source.x), Math.abs(destination.y-source.y));
  }

  public static class Coordinate {
    public long x;
    public long y;

    public Coordinate(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }

  public static Coordinate[] readCoordinates() throws IOException {
    int lineCount = Integer.parseInt(readLine().trim());
    Coordinate[] coordinates = new Coordinate[lineCount];
    for (int i = 0; i < lineCount; i++) {
      String[] stringCoordinate = readLine().split(" ");
      coordinates[i] = new Coordinate(Long.parseLong(stringCoordinate[0]), Long.parseLong(stringCoordinate[1]));
    }

    return coordinates;
  }

  public static String readLine() throws IOException {
    return in.readLine();
  }

}
