import java.io.IOException;
import java.io.RandomAccessFile;

public class Flights extends FileHandler {

    protected Tickets tickets;
    protected Users users;

    public Flights(Tickets tickets, Users users, RandomAccessFile raf) {
        super(raf, 20, 141);
        this.tickets = tickets;
        this.users = users;
    }

    public void flightSchedule() throws IOException {
        System.out.print(DetailsAndData.flightHead());
        searcher(String.format("%130s", "")).forEach(Menu::printFlight);
    }

    public boolean removeTicket(String id) throws IOException {
        for (String ticket : tickets.searcherTicket(String.format("%40s", " ") + String.format("%20s", id))) {
            users.removeTicket(ticket.substring(20, 40).trim(), new Flight().convertToObj(findValue(id.trim())).getPrice());
            tickets.remove(ticket.substring(0, 20).trim(), new Ticket().toString());
        }
        return true;
    }

    public boolean removeFlight(String id) throws IOException {
        if (existValue(id) != -1) {
            removeTicket(id);
            remove(id, new Flight().toString());
            return true;
        }
        return false;
    }
}
