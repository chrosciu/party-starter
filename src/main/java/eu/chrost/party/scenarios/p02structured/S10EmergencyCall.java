import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import eu.chrost.party.disasters.EmergencyCallException;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

List<String> emergencyCallScenario() {
    try (var alice = StructuredTaskScope.<String>open()) {
        var bobTask = alice.fork(Bob::cookPasta);
        var carolTask = alice.fork(Carol::prepareSauce);
        if (true) {
            println("Emergency call");
            throw new EmergencyCallException();
        }
        return List.of(bobTask.get(), carolTask.get());
    } catch (Exception e) {
        println("Something went wrong: " + e.getMessage());
        return List.of();
    }
}

void main() {
    Timeline.start();
    Timeline.setActor("Alice");
    println(emergencyCallScenario());
}
