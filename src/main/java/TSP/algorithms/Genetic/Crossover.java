package TSP.algorithms.Genetic;

import java.util.List;

public interface Crossover {
    List<Tour> crossover(List<Tour> population);
}
