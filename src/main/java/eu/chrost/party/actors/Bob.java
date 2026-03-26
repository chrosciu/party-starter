package eu.chrost.party.actors;

import eu.chrost.party.disasters.BurntKettleException;
import lombok.SneakyThrows;

import static eu.chrost.party.util.Timeline.println;
import static eu.chrost.party.util.Timeline.setActor;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

public class Bob {
    @SneakyThrows
    public static String cookPasta() {
        setActor("Bob");
        try {
            println("Boil water");
            sleep(ofSeconds(5));
            println("Add pasta");
            sleep(ofSeconds(1));
            println("Watch the time");
            sleep(ofSeconds(8));
            println("Pasta is ready");
            return "Pasta";
        } catch (InterruptedException e) {
            println("Pasta cooking has been interrupted");
            throw  e;
        }
    }

    @SneakyThrows
    public static String burnKettle() {
        setActor("Bob");
        println("Boil water");
        sleep(ofSeconds(3));
        println("Kettle is burning...");
        throw new BurntKettleException();
    }
}
