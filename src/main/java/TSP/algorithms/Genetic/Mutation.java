package TSP.algorithms.Genetic;

import java.util.List;

public interface Mutation {
    List<Tour> mutate(List<Tour> population);
}
