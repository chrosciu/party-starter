import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import static java.lang.IO.println;

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
    println(optimisticScenario());
}
