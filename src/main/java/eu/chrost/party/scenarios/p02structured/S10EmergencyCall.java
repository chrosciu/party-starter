import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import eu.chrost.party.disasters.EmergencyCallException;
import lombok.SneakyThrows;

import static java.lang.IO.println;

@SneakyThrows
List<String> emergencyCallScenario() {
    try (var alice = StructuredTaskScope.<String>open()) {
        var bobTask = alice.fork(Bob::cookPasta);
        var carolTask = alice.fork(Carol::prepareSauce);
        if (true) {
            println("Emergency call");
            throw new EmergencyCallException();
        }
        return List.of(bobTask.get(), carolTask.get());
    }
}

void main() {
    println(emergencyCallScenario());
}
