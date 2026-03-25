import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;
import eu.chrost.party.actors.Dave;
import lombok.SneakyThrows;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;
import static java.util.concurrent.StructuredTaskScope.Subtask.State.SUCCESS;

class AllSuccessfulTasksJoiner<T> implements StructuredTaskScope.Joiner<T, Stream<T>> {
    private final Queue<T> results = new ConcurrentLinkedQueue<>();

    @Override
    public boolean onComplete(StructuredTaskScope.Subtask<? extends T> subtask) {
        if (subtask.state() == SUCCESS) {
            results.add(subtask.get());
        }
        return false;
    }

    @Override
    public Stream<T> result() throws Throwable {
        return results.stream();
    }
}

@SneakyThrows
List<String> allSuccessfulScenario() {
    try (var alice = StructuredTaskScope.open(new AllSuccessfulTasksJoiner<String>())) {
        alice.fork(Bob::burnKettle);
        alice.fork(Carol::prepareSauce);
        alice.fork(Dave::makeSalad);
        return alice.join().toList();
    }
}

void main() {
    Timeline.start();
    println(allSuccessfulScenario());
}
