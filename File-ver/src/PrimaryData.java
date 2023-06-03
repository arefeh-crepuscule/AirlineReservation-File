import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PrimaryData {

    private final ArrayList<String> city = new ArrayList<>(Arrays.asList("yazd", "kerman", "mashhad", "shiraz", "tehran", "esfahan", "ardabil", "kish", "ahvaz", "hamadan", "karaj", "semnan", "ilam", "gheshm", "amol", "tabas", "savah", "kashan"));
    private Flights flights;
    Random rand = new Random();

    public PrimaryData(Flights flights) throws IOException {
        this.flights = flights;
        makePrimaryFlights();
    }

    /**
     * make 10 new flights as default
     */
    public void makePrimaryFlights() throws IOException {
        for (int i = 0; i < 10; i++) {
            String temp = setOrigin();
            Flight flight = new Flight(temp, setDestination(temp), setDate(), setTime(), setPrice(), 245);
            flight.makeFlightId();
            flights.add(flight.toString());
        }
    }

    /**
     * set random price in flight's price parameter
     */
    private int setPrice() {
        int[] price = {1500000, 1400000, 1700000, 1100000, 1350000, 1240000, 1432000, 1640000, 1030000, 1090000, 1300000};
        return (price[rand.nextInt(price.length)]);
    }

    /**
     * set random time in flight's time parameter
     */
    private String setTime() {
        int hour = rand.nextInt(23);
        String a = String.format("0%d", hour).substring(String.format("0%d", hour).length() - 2);
        hour = rand.nextInt(59);
        String b = String.format("0%d", hour).substring(String.format("0%d", hour).length() - 2);
        return a + ":" + b;

    }

    /**
     * set random date in flight's date parameter
     **/
    private String setDate() {
        int hour = rand.nextInt(12);
        String a = String.format("0%d", hour).substring(String.format("0%d", hour).length() - 2);
        hour = rand.nextInt(30);
        String b = String.format("0%d", hour).substring(String.format("0%d", hour).length() - 2);
        return rand.nextInt(1401, 1410) + "-" + a + "-" +b;

    }

    /**
     * set random city in flight's origin and destination parameter
     **/
    private String setOrigin() {
        return city.get(rand.nextInt(city.size()));
    }

    private String setDestination(String origin) {
        city.remove(origin);
        String destination = city.get(rand.nextInt(city.size()));
        city.add(origin);
        return destination;
    }
}


