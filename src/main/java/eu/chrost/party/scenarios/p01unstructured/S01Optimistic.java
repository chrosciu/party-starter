import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import lombok.SneakyThrows;

import static java.lang.IO.println;

@SneakyThrows
List<String> optimisticScenario()  {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::cookPasta);
        var carolTask = alice.submit(Carol::prepareSauce);
        return List.of(bobTask.get(), carolTask.get());
    }
}

void main() {
    println(optimisticScenario());
}
