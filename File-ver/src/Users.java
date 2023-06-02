import java.io.IOException;
import java.io.RandomAccessFile;

public class Users extends FileHandler{

    public Users(RandomAccessFile raf) {
        super(raf, 20, 81);
    }

    public User check(String user, String pass) throws IOException {
        User user1 = new User().convertToObj(findValue(user));
        if(user1 == null) return null;
        if(user1.getPassword().trim().equals(pass))return user1;
        return null;
    }
    public void removeTicket(String ticket) throws IOException {
        User user = new User ().convertToObj((findValue(ticket.substring(20,40).trim())));
        user.addNotify();
        user.updateCharge(new Flight().convertToObj(ticket.substring(40,60)).getPrice());
        rewrite(user.toString());
    }

}
