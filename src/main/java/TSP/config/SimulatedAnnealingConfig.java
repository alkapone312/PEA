package TSP.config;

import TSP.algorithms.utils.FirstSolutionGeneration;
import TSP.algorithms.utils.GreedyFirstSolutionGeneration;
import TSP.algorithms.utils.NeighborGeneration;
import TSP.algorithms.utils.SwapRandomNodesNeighbors;

public class SimulatedAnnealingConfig {
    public static NeighborGeneration neighborGeneration = new SwapRandomNodesNeighbors();
    public static FirstSolutionGeneration firstSolutionGeneration = new GreedyFirstSolutionGeneration();
    public static double temperatureChange = 0.999;
}
