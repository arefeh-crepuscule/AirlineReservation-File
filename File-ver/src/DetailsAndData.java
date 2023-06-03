import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.SplittableRandom;

public class DetailsAndData {
    protected PrimaryData primaryData;
    protected Scanner scanner = new Scanner(System.in);
    protected RandomAccessFile flightsRaf =new RandomAccessFile("flights.dat","rw");
    protected Users users = new Users(new RandomAccessFile("users.dat","rw"));
    protected Tickets tickets = new Tickets(new RandomAccessFile("tickets.dat","rw"),users);
    protected Flights flights = new Flights(tickets,users,flightsRaf);

    public DetailsAndData() throws FileNotFoundException {
    }

    public String inputProcess(String text){
        System.out.println(text);
        return scanner.nextLine();
    }
    public int inputNumber (){
        while (true) {
            String input =scanner.nextLine();
            if (input.matches("\\d+")) return Integer.parseInt(input);
            else System.out.println("Incorrect input!!");
        }
    }
    public static String formatting(String input) {
        return String.format("%20s", input).substring(0, 20);
    }

    public static void printFlight(String flight){
        System.out.print(flight);
    }

    protected Flight makeFlight() throws FileNotFoundException {
        String origin = inputProcess("Origin :");
        String destination = inputProcess("Destination :");
        String date = getDate();
        String time = getTime();
        System.out.println("Price :");
        int price = inputNumber();
        System.out.println("Seats : ");
        int seats = inputNumber();
        return new Flight(origin, destination, date, time, price, seats);
    }
    protected String getTime(){
        String tempDate= inputProcess("Enter the time  (like hh:mm)");
        while ((!tempDate.matches("^[0-2]\\d:[0-5]\\d$") && (!tempDate.equals("")))) {
            System.out.println("incorrect input!!");
            tempDate= inputProcess("Enter the time  (like hh:mm)");
        }
        return tempDate;
    }
    protected String getDate() {
        String tempDate= inputProcess("Enter the date (like: YYYY-MM-DD)");
        while ((!tempDate.matches("^\\d(4)-[0-1]\\d-[0-3]\\d$") &&(!tempDate.equals("")))) {
            System.out.println("incorrect input!!");
            tempDate= inputProcess("Enter the date (like: YYYY-MM-DD)");
        }
        return tempDate;
    }
    protected static  String flightHead(){
        return String.format("%19s|%19s|%19s|%19s|%19s|%19s|%19s|\n\n","Flight ID","Origin","Destination","Date","Time","Price","Seats");
    }
    protected static String TicketHead(){
        return String.format("%19s|%19s|%19s|%19s|%19s|%19s|%19s|%19s|\n\n","Ticket ID","Flight ID","Origin","Destination","Date","Time","Price","Seats");
    }

}
