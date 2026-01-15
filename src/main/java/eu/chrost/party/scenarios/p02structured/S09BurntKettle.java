import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import static java.lang.IO.println;

@SneakyThrows
List<String> burntKettleScenario() {
    try (var alice = StructuredTaskScope.<String>open()) {
        var bobTask = alice.fork(Bob::burnKettle);
        var carolTask = alice.fork(Carol::prepareSauce);
        alice.join();
        return List.of(bobTask.get(), carolTask.get());
    } catch (Exception e) {
        println("Something went wrong: " + e.getMessage());
        return List.of();
    }
}

void main() {
    println(burntKettleScenario());
}
