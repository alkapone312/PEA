package TSP.config;

import TSP.algorithms.Genetic.*;

public class GeneticAlgorithmConfig {
    public static int population = 1000;
    public static Crossover crossover = new CycleCrossover(0.8);
    public static Mutation mutation = new SwapMutation(0.01);
    public static Selection selection = new TournamentSelection();
}
