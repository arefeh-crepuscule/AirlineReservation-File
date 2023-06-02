import java.io.IOException;

public class AdminMenu extends Menu{
    /**
     * when set thicket class change every thing need or access ticket class
     */
    public AdminMenu() throws IOException {
        adminMenu();
    }

    public void printAdminMenu() {
        System.out.print("""
                ::::::::::::::::::::::::::::::::::::::::
                           Admin MENU OPTIONS
                ::::::::::::::::::::::::::::::::::::::::
                 ......................................
                    <1> Add
                    <2> Update
                    <3> Remove
                    <4> Flight schedules
                    <0> Sign out
                """);
    }

    public void adminMenu() throws IOException {
        label:
        while (true) {
            printAdminMenu();
            switch (AdminEnum.getValue(inputNumber())) {
                case SIGN_OUT -> {
                    break label;
                }
                case ADD_FLIGHT -> addProcess();
                case UPDATE_FLIGHT -> updateProcess();
                case REMOVE_FLIGHT -> removeProcess();
                case FLIGHT_SCHEDULE -> flights.flightSchedule();

                default -> System.out.println("Incorrect input ! try again");
            }
            inputProcess("Enter to continue...");
        }
    }

    private void addProcess() throws IOException {
        Flight flight = makeFlight();
        flight.makeFlightId();
        if (!flights.add(flight.toString()))
            System.out.println("there is a flight like this already!!");
    }

    private void removeProcess() throws IOException {
        if (!flights.removeFlight(chooseFlight()))
            System.out.println("there is no flight");

    }

    private String chooseFlight() throws IOException {
        flights.flightSchedule();
        return (inputProcess("Enter Flight ID : "));
    }
    private void updateProcess() throws IOException {
        String flight = flights.findValue(chooseFlight());
        if (flight == null) System.out.println("there is no flight");
        else {
            System.out.println("choose every information that you want filter ,(if dont ,press Enter, for price and seats (0)");
            Flight flightNew = makeFlight();
            Flight flightOld =new Flight().convertToObj(flight);
            flightOld.update(flightNew);
            flights.rewrite(flightOld.toString());
            tickets.removeFlight(flightOld.getFlightId());
        }
    }
}
