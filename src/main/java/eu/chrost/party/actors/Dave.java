package eu.chrost.party.actors;

import lombok.SneakyThrows;

import static eu.chrost.party.util.Timeline.println;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

public class Dave {
    @SneakyThrows
    public static String makeSalad() {
        println("Wash and chop vegetables");
        sleep(ofSeconds(3));
        println("Prepare the dressing");
        sleep(ofSeconds(1));
        println("Mix everything together");
        sleep(ofSeconds(1));
        println("Salad is ready");
        return "Salad";
    }
}
