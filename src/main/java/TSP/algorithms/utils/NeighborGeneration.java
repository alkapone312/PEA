package TSP.algorithms.utils;

import java.util.List;

public interface NeighborGeneration {
    List<List<Integer>> generateNeighbors(List<Integer> solution, int numberOfNeightbors);
    String getName();
}
