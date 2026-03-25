import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

@SneakyThrows
List<String> optimisticScenario()  {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::cookPasta);
        var carolTask = alice.submit(Carol::prepareSauce);
        return List.of(bobTask.get(), carolTask.get());
    }
}

void main() {
    Timeline.start();
    println(optimisticScenario());
}
