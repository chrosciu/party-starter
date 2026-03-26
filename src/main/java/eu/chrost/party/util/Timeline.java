package eu.chrost.party.util;

import java.time.Duration;
import java.time.Instant;

public class Timeline {
    private static Instant start;
    private static final ThreadLocal<String> actor = new ThreadLocal<>();

    public static void start() {
        start = Instant.now();
    }

    public static void setActor(String name) {
        actor.set(name);
    }

    public static void println(Object msg) {
        long millis = Duration.between(start, Instant.now()).toMillis();
        String name = actor.get();
        String prefix = name != null ? "[" + name + "] " : "";
        java.lang.IO.println("[+%d.%03ds] %s%s".formatted(millis / 1000, millis % 1000, prefix, msg));
    }
}
