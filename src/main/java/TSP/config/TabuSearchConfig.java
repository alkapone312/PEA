package TSP.config;

import TSP.algorithms.utils.FirstSolutionGeneration;
import TSP.algorithms.utils.GreedyFirstSolutionGeneration;
import TSP.algorithms.utils.NeighborGeneration;
import TSP.algorithms.utils.SwapRandomNodesNeighbors;

public class TabuSearchConfig {
    public static NeighborGeneration neighborGeneration = new SwapRandomNodesNeighbors();
    public static FirstSolutionGeneration firstSolutionGeneration = new GreedyFirstSolutionGeneration();
}
