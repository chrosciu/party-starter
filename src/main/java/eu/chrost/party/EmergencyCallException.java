package eu.chrost.party;

public class EmergencyCallException extends RuntimeException {
    public EmergencyCallException() {
        super("Emergency call with bad news :(");
    }
}
