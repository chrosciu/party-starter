package eu.chrost.party;

import lombok.SneakyThrows;

import static java.lang.IO.println;
import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

public class Bob {
    @SneakyThrows
    public static String cookPasta() {
        println("Boil water");
        sleep(ofSeconds(5));
        println("Add pasta");
        sleep(ofSeconds(1));
        println("Watch the time");
        sleep(ofSeconds(8));
        println("Pasta is ready");
        return "Pasta";
    }

    @SneakyThrows
    public static String burnKettle() {
        println("Boil water");
        sleep(ofSeconds(3));
        throw new BurntKettleException();
    }
}
