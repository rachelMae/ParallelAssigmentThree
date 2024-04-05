import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Weather {
    static List<Double> temperatureReadings = new ArrayList<>();
    static Random random = new Random();
    static int counter = 0;

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(() -> {
                while (counter < 60) {
                    generateTemperature();
                }
            });
            thread.start();
            threads.add(thread);
        }
        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // report largest temperature difference in 10 min or 10 numbers
        int chunkSize = 10;
        double largestDifference = 0;
        int intervalStart = 0;

        for (int i = 0; i <= temperatureReadings.size() - chunkSize; i++) {
            double maxTemp = temperatureReadings.get(i);
            double minTemp = temperatureReadings.get(i);

            for (int j = i + 1; j < i + chunkSize; j++) {
                maxTemp = Math.max(maxTemp, temperatureReadings.get(j));
                minTemp = Math.min(minTemp, temperatureReadings.get(j));
            }

            double difference = Math.abs(maxTemp - minTemp);

            if (difference > largestDifference) {
                largestDifference = difference;
                intervalStart = i;
            }
        }

        System.out.println("Largest temperature difference observed in the interval: "
                + intervalStart + " - " + (intervalStart + chunkSize)
                + " with difference: " + largestDifference);

        Collections.sort(temperatureReadings); // sort the temperatures

        // Report top 5 highest temperatures
        System.out.println("Top 5 highest temperatures:");
        for (int i = temperatureReadings.size() - 1; i >= temperatureReadings.size() - 5; i--) {
            System.out.println(temperatureReadings.get(i));
        }

        // Report top 5 lowest temperatures
        System.out.println("Top 5 lowest temperatures:");
        for (int i = 0; i < 5; i++) {
            System.out.println(temperatureReadings.get(i));
        }
    }

    private static void generateTemperature() {
        if (counter < 60) {
            // Generate a random temperature between -100F and 70F
            double temp = random.nextDouble() * (70 + 100) - 100;
            temperatureReadings.add(temp);
            counter++;
        }
    }
}
