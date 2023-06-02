public enum AdminEnum {
    SIGN_OUT,
    ADD_FLIGHT,
    UPDATE_FLIGHT,
    REMOVE_FLIGHT,
    FLIGHT_SCHEDULE;

    public static AdminEnum getValue(int ordinal){
        return values()[ordinal];
    }
}
