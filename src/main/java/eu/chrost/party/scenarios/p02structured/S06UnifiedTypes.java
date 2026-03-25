import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

@SneakyThrows
List<String> unifiedTypesScenario() {
    try (var alice = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>allSuccessfulOrThrow())) {
        alice.fork(Bob::cookPasta);
        alice.fork(Carol::prepareSauce);
        return alice.join().map(StructuredTaskScope.Subtask::get).toList();
    }
}

void main() {
    Timeline.start();
    println(unifiedTypesScenario());
}
