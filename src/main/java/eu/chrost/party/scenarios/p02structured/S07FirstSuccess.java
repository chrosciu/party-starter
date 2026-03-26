import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

@SneakyThrows
String firstSuccessScenario() {
    try (var alice = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow())) {
        alice.fork(Bob::cookPasta);
        alice.fork(Carol::prepareSauce);
        return alice.join();
    }
}

void main() {
    Timeline.start();
    Timeline.setActor("Alice");
    println(firstSuccessScenario());
}
