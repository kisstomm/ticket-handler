package tickethandler.common.enums;

public enum ErrorType {
    // PARTNER errors
    PARTNER_EVENT_NOT_FOUND(90001, "Nem létezik ilyen esemény!"),
    PARTNER_SEAT_NOT_FOUND(90002, "Nem létezik ilyen szék!"),
    PARTNER_SEAT_IS_NOT_FOR_EVENT(90009, "A megadott szék nem a megadott eseményhez tartozik!"),
    PARTNER_SEAT_IS_SOLD(90010, "Már lefoglalt székre nem lehet jegyet eladni!"),
    PARTNER_EVENT_STARTED(90011, "Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!"),

    // TICKET errors
    TICKET_EVENT_NOT_FOUND(20001, "Nem létezik ilyen esemény!"),
    TICKET_SEAT_NOT_FOUND(20002, "Nem létezik ilyen szék!"),
    TICKET_SEAT_IS_NOT_FOR_EVENT(20009, "A megadott szék nem a megadott eseményhez tartozik!"),
    TICKET_SEAT_IS_SOLD(20010, "Már lefoglalt székre nem lehet jegyet eladni!"),
    TICKET_EVENT_STARTED(20011, "Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!"),
    TICKET_PARTNER_NOT_REACHABLE(20404, "A külső rendszer nem elérhető!"),

    // CORE errors
    CORE_CARD_NOT_FOR_USER(10100, "Ez a bankkártya nem ehhez a felhasználóhoz tartozik!"),
    CORE_NOT_ENOUGH_MONEY(10101, "A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!"),

    // API errors
    API_TICKET_NOT_REACHABLE(30004, "Egy belső rendszer nem elérhető!"),

    // COMMON errors
    NO_ERROR(0, "");

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
