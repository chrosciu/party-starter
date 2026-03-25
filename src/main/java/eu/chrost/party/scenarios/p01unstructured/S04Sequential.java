import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;

import eu.chrost.party.util.Timeline;
import static eu.chrost.party.util.Timeline.println;

List<String> sequentialScenario() {
    var pasta = Bob.cookPasta();
    var sauce = Carol.prepareSauce();
    return List.of(pasta, sauce);
}

void main() {
    Timeline.start();
    println(sequentialScenario());
}
