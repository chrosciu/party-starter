import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import static java.lang.IO.println;

@SneakyThrows
List<String> burntKettleScenario() {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::burnKettle);
        var carolTask = alice.submit(Carol::prepareSauce);
        return List.of(bobTask.get(), carolTask.get());
    }
}

void main() {
    println(burntKettleScenario());
}

