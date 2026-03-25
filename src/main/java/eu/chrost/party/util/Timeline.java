package eu.chrost.party.util;

import java.time.Duration;
import java.time.Instant;

public class Timeline {
    private static Instant start;

    public static void start() {
        start = Instant.now();
    }

    public static void println(Object msg) {
        long millis = Duration.between(start, Instant.now()).toMillis();
        java.lang.IO.println("[+%d.%03ds] %s".formatted(millis / 1000, millis % 1000, msg));
    }
}
