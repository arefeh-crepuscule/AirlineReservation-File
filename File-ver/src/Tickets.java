import java.io.IOException;
import java.io.RandomAccessFile;

public class Tickets extends FileHandler{
    public Users users;
    public Tickets(RandomAccessFile raf ,Users users) {
        super(raf, 20, 61);
        this.users =users;
    }

//    public boolean removeFlight(String id) throws IOException {
//        for (String ticket : searcher(String.format("%40s"," ")+String.format("%20s",id))) {
//            System.out.println(ticket);
//
//            users.removeTicket(ticket);
//            remove(ticket.substring(0,20),new Ticket().toString());
//        }
//        return true;
//    }
}
