package TSP.algorithms.Genetic;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements Selection {
    private Random r = new Random();

    @Override
    public Tour select(List<Tour> population) {
        Tour specie1 = population.get(r.nextInt(population.size()));
        Tour specie2 = population.get(r.nextInt(population.size()));

        while(specie1 == specie2) {
            specie2 = population.get(r.nextInt(population.size()));
        }

        if(specie1.getCost() < specie2.getCost()) {
            return specie1;
        }

        return specie2;
    }
}
