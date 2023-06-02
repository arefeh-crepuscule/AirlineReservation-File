public enum UserEnum {
    SIGN_OUT,
    CHANGE_PASSWORD,
    SEARCH_FLIGHT,
    BOOKING_TICKET,
    TICKET_CANCELLATION,
    BOOKED_TICKETS,
    ADD_CHARGE,
    FLIGHT_SCHEDULE;


    public static UserEnum getValue(int ordinal){
        return values()[ordinal];
    }

}
