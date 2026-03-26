import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

@SneakyThrows
List<String> optimisticScenario() {
    try (var alice = StructuredTaskScope.<String>open()) {
        var bobTask = alice.fork(Bob::cookPasta);
        var carolTask = alice.fork(Carol::prepareSauce);
        alice.join();
        return List.of(bobTask.get(), carolTask.get());
    }
}

void main() {
    Timeline.start();
    Timeline.setActor("Alice");
    println(optimisticScenario());
}
