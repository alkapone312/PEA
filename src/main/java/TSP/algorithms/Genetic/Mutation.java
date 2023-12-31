package TSP.algorithms.Genetic;

import java.util.List;

public interface Mutation {
    List<List<Integer>> mutate(List<List<Integer>> population);
}
