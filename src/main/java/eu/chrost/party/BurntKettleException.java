package eu.chrost.party;

public class BurntKettleException extends RuntimeException {
    public BurntKettleException() {
        super("The kettle burned out");
    }
}
