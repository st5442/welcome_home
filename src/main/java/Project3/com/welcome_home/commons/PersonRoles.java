package Project3.com.welcome_home.commons;

public enum PersonRoles {
    STAFF("staff"),
    DONOR("donor"),
    SUPERVISOR("supervisor"),
    DELIVERY("delivery");

    private final String textRepresentation;

    private PersonRoles(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    @Override public String toString() {
        return textRepresentation;
    }
}
