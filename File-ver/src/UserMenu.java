import java.io.IOException;

public class UserMenu extends Menu{
    User user;
    public UserMenu(User user) throws IOException {
        this.user = user;
        userMenu();
    }
    public void printUserMenu() {
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::
                         PASSENGER MENU OPTIONS
                ::::::::::::::::::::::::::::::::::::::::
                 ......................................
                    <1> Change password
                    <2> Search flight tickets
                    <3> Booking ticket
                    <4> Ticket cancellation
                    <5> Booked tickets
                    <6> Add charge
                    <7> Flight schedules
                    <0> Sign out
                """);
    }

    public void userMenu() throws IOException {
        printNotify();
        label:
        while (true) {
            printUserMenu();
            switch (UserEnum.getValue(inputNumber())) {
                case CHANGE_PASSWORD -> changePassword();
                case SEARCH_FLIGHT -> searchProcess();
                case BOOKING_TICKET -> bookingProcess();
                case TICKET_CANCELLATION -> cancellationProcess();
                case BOOKED_TICKETS -> showBookedProcess();// not done because the user searcher in file
                case ADD_CHARGE -> addChargeProcess();
                case FLIGHT_SCHEDULE -> flights.flightSchedule();
                case SIGN_OUT -> {
                    users.rewrite(user.toString());
                    break label;
                }
                default -> System.out.println("Incorrect input ! try again");
            }
            inputProcess("Enter to continue...");
        }
    }

    private void printNotify() {
        if (user.getNotify() == 1)
            inputProcess("Admin changed some details , so %2d flight is cancelled for you!\n"+"Enter to continue...");
        else if (user.getNotify() >1)
            inputProcess("Admin changed some details , so %2d flight are cancelled for you!\n"+"Enter to continue...");

        user.setNotify(0);

    }

    private void changePassword() {
        String password = inputProcess("previous password :");
        if (user.getPassword().trim().equals(password)) {
            user.setPassword(inputProcess("new password :"));
        } else System.out.println("incorrect input");
    }
    private void searchProcess() throws IOException {
        System.out.println("Enter your selected filter , if not (press Enter 0)");
        Flight flight = makeFlight();
        System.out.println("Result : ");
        for (String flight1 : flights.searcher(flight.toString()))
            System.out.println(flight1);
    }

    private void bookingProcess() throws IOException {
        flights.flightSchedule();
        String flight = flights.findValue(inputProcess("Flight ID :"));
        if (flight != null && new Flight().convertToObj(flight).getPrice() <= user.getCharge()) {
            Flight flight1=new Flight().convertToObj(flight);
            Ticket ticket =new Ticket(flight1.getFlightId(), user.getUsername());
            tickets.add(ticket.toString());
            flight1.updateSeats(-1);
            user.updateCharge(-(flight1.getPrice()));
            System.out.println("Done...");
            flights.rewrite(flight1.toString());
        } else if (flight == null) {
            System.out.println("There is no flight like with this flight ID !!");
        } else {
            System.out.println("Insufficient inventory !!");
        }

    }
    private void cancellationProcess() throws IOException {
        String ticket = tickets.findValue(inputProcess("Ticket ID :"));
        if (ticket != null) {
            Flight flight =new Flight().convertToObj(flights.findValue(ticket.substring(40,60).trim()));
            user.updateCharge(flight.getPrice());
            flight.updateSeats(1);
            flights.rewrite(flight.toString());
            tickets.remove(ticket.substring(0,20).trim() , new Ticket().toString());
        } else System.out.println("No ticket with this ID");
    }
    private void showBookedProcess() throws IOException {
        for (String ticket : tickets.searcher(String.format("%20s","")+user.getUsername()))
            System.out.println(ticket.substring(0,20)+flights.findValue(ticket.substring(40,60).trim()));
    }


    private void addChargeProcess() {
        System.out.printf("Your current charge :\t%10d\n", user.getCharge());
        System.out.println("How many do you want to charge :");
        int price = inputNumber();
        if (price < 0) System.out.println("Incorrect input");
        else {
            user.updateCharge(price);
            System.out.print("Done,");
        }
    }
}
