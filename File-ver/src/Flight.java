import java.io.FileNotFoundException;
import java.util.Random;

public class Flight {
    Random rand = new Random();
    private String flightId;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int price;
    private int seats;

    public Flight(String origin, String destination, String date, String time, int price, int seats) throws FileNotFoundException {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = seats;
    }

    public Flight() {

    }

    public void makeFlightId() {
        flightId = "WH_" + origin.charAt(0) + destination.charAt(0) + "_" + rand.nextInt(10) + rand.nextInt(10);
    }
    public void updateSeats(int num) {
        seats += num;
    }
    public String getFlightId() {
        return flightId;
    }

    public String toString() {
        return
                Menu.formatting(flightId) + Menu.formatting(origin) + Menu.formatting(destination) + Menu.formatting(date) + Menu.formatting(time) + Menu.formatting(Integer.toString(price)) +Menu.formatting(Integer.toString(seats));
    }
    public Flight convertToObj(String obj){
        flightId = obj.substring(0, 20);
        origin = obj.substring(20, 40);
        destination = obj.substring(40, 60);
        date = obj.substring(60, 80);
        time = obj.substring(80, 100);
        price = Integer.parseInt(obj.substring(100, 120).trim());
        seats = Integer.parseInt(obj.substring(120, 140).trim());
        return this;
    }
    public void update(Flight flightNew) {
        if (!flightNew.origin.isEmpty()) origin = flightNew.origin;
        if (!flightNew.destination.isEmpty()) destination = flightNew.destination;
        if (!flightNew.date.isEmpty()) date = flightNew.date;
        if (!flightNew.time.isEmpty()) time = flightNew.time;
        if (flightNew.price != 0) price = flightNew.price;
        if (flightNew.seats != 0) seats = flightNew.seats;
    }


    public int getPrice() {
        return price;
    }
}
