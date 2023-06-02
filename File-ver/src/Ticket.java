import java.util.Random;

public class Ticket {
    Random random = new Random();
    private String flight;
    private String user;
    private String ticketId;

    public Ticket() {
        user = null;
        ticketId = null;
        flight = null;
    }

    public Ticket(String flight, String user) {
        this.flight = flight;
        this.user = user;
        ticketId = "TH-"+flight.trim()+"-"+user.trim()+"-"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10);
    }
    public String toString() {
        return Menu.formatting(ticketId)+Menu.formatting(user)+ Menu.formatting(flight);
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getFlight() {
        return flight;
    }

    public String getUser() {
        return user;
    }
    public Ticket convertToObj(String obj){
        ticketId = obj.substring(0,20);
        flight=obj.substring(20,40);
        user=obj.substring(40,60);
        return this;
    }
}
