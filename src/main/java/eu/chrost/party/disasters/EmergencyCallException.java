package eu.chrost.party.disasters;

public class EmergencyCallException extends RuntimeException {
    public EmergencyCallException() {
        super("Emergency call with bad news :(");
    }
}
