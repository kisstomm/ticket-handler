package tickethandler.common.enums;

public enum ErrorType {
    PARTNER_EVENT_NOT_FOUND(90001, "Nem létezik ilyen esemény!"),

    TICKET_EVENT_NOT_FOUND(20001, "Nem létezik ilyen esemény!"),
    TICKET_PARTNER_NOT_REACHABLE(20004, "A külső rendszer nem elérhető!");


    private final int code;
    private final String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
