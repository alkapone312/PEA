package TSP.algorithms.Genetic;

import java.util.List;

public interface Crossover {
    List<List<Integer>> crossover(List<List<Integer>> population);
}
