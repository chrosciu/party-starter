package eu.chrost.party.disasters;

public class BurntKettleException extends RuntimeException {
    public BurntKettleException() {
        super("The kettle burned out");
    }
}
