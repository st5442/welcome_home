package Project3.com.welcome_home.commons;

public enum DeliveryStatus {
    RECEIVED("ORDER RECEIVED"),
    PREPARING("PREPARING"),
    SHIPPED("SHIPPED"),
    IN_TRANSIT("IN TRANSIT"),
    DELIVERED("DELIVERED");

    private final String textRepresentation;

    private DeliveryStatus(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    @Override public String toString() {
        return textRepresentation;
    }
}
