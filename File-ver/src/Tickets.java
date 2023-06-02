import java.io.IOException;
import java.io.RandomAccessFile;

public class Tickets extends FileHandler{
    private Users users;
    public Tickets(RandomAccessFile raf ,Users users) {
        super(raf, 20, 61);
        this.users =users;
    }

    public boolean removeFlight(String id) throws IOException {
        for (String ticket : searcher(String.format("%20s"," ")+id.trim()+String.format("%20s"," "))) {
            users.removeTicket(ticket);
            remove(ticket.substring(0,20),new Ticket().toString());
        }
        return true;
    }
}
