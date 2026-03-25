package eu.chrost.party.actors;

import lombok.SneakyThrows;

import static eu.chrost.party.util.Timeline.println;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

public class Carol {
    @SneakyThrows
    public static String prepareSauce() {
        try {
            println("Sauté onions and garlic");
            sleep(ofSeconds(7));
            println("Add tomatoes and seasoning");
            sleep(ofSeconds(2));
            println("Simmer everything");
            sleep(ofSeconds(7));
            println("Sauce is ready");
            return "Sauce";
        } catch (InterruptedException e) {
            println("Sauce preparation has been interrupted");
            throw  e;
        }
    }
}
