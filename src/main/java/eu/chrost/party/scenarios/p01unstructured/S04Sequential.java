import eu.chrost.party.actors.Bob;
import eu.chrost.party.actors.Carol;

import static java.lang.IO.println;

List<String> sequentialScenario() {
    var pasta = Bob.cookPasta();
    var sauce = Carol.prepareSauce();
    return List.of(pasta, sauce);
}

void main() {
    println(sequentialScenario());
}
