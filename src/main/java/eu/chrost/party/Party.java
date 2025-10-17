import eu.chrost.party.Bob;
import eu.chrost.party.Carol;
import eu.chrost.party.Dave;
import eu.chrost.party.EmergencyCallException;
import lombok.SneakyThrows;

import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;

import static java.lang.IO.println;
import static java.util.concurrent.StructuredTaskScope.Subtask.State.SUCCESS;

@SneakyThrows
List<String> optimisticScenario() {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::cookPasta);
        var carolTask = alice.submit(Carol::prepareSauce);
        return List.of(bobTask.get(), carolTask.get());
    }
}

@SneakyThrows
List<String> burntKettleScenario() {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::burnKettle);
        var carolTask = alice.submit(Carol::prepareSauce);
        return List.of(bobTask.get(), carolTask.get());
    }
}

@SneakyThrows
List<String> emergencyCallScenario() {
    try (var alice = Executors.newVirtualThreadPerTaskExecutor()) {
        var bobTask = alice.submit(Bob::cookPasta);
        var carolTask = alice.submit(Carol::prepareSauce);
        if (true) {
            println("Emergency call");
            throw new EmergencyCallException();
        }
        return List.of(bobTask.get(), carolTask.get());
    }
}

List<String> sequentialScenario() {
    var pasta = Bob.cookPasta();
    var sauce = Carol.prepareSauce();
    return List.of(pasta, sauce);
}

@SneakyThrows
List<String> structuredOptimisticScenario() {
    try (var alice = StructuredTaskScope.<String>open()) {
        var bobTask = alice.fork(Bob::cookPasta);
        var carolTask = alice.fork(Carol::prepareSauce);
        alice.join();
        return List.of(bobTask.get(), carolTask.get());
    }
}


@SneakyThrows
List<String> structuredBurntKettleScenario() {
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

@SneakyThrows
List<String> structuredOptimisticScenarioWithUnifiedTypes() {
    try (var alice=
                 StructuredTaskScope.open(Joiner.<String>allSuccessfulOrThrow())) {
        alice.fork(Bob::cookPasta);
        alice.fork(Carol::prepareSauce);
        return alice.join().map(Subtask::get).toList();
    }
}

@SneakyThrows
String firstSuccessScenario() {
    try (var alice=
                 StructuredTaskScope.open(Joiner.<String>anySuccessfulResultOrThrow())) {
        alice.fork(Bob::cookPasta);
        alice.fork(Carol::prepareSauce);
        return alice.join();
    }
}

class AllSuccessfulTasksJoiner<T> implements Joiner<T, Stream<T>> {
    private final Queue<T> results = new ConcurrentLinkedQueue<>();

    @Override
    public boolean onComplete(Subtask<? extends T> subtask) {
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
    try (var alice
                 = StructuredTaskScope.open(new AllSuccessfulTasksJoiner<String>())) {
        alice.fork(Bob::burnKettle);
        alice.fork(Carol::prepareSauce);
        alice.fork(Dave::makeSalad);
        return alice.join().toList();
    }
}

void main() {
    println(optimisticScenario());
//    println(burntKettleScenario());
//    println(emergencyCallScenario());
//    println(sequentialScenario());
//    println(structuredOptimisticScenario());
//    println(structuredOptimisticScenarioWithUnifiedTypes());
//    println(firstSuccessScenario());
//    println(allSuccessfulScenario());
//    println(structuredBurntKettleScenario());
}



