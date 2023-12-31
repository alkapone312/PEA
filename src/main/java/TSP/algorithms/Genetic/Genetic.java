package TSP.algorithms.Genetic;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.algorithms.utils.AlgorithmObserver;
import TSP.data.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Genetic implements Algorithm {
    private AlgorithmObserver observer;
    private boolean run = true;
    private int[][] distanceMatrix;

    private Crossover crossover;
    private Mutation mutation;
    private double populationSize;
    private double numberOfGenerations;

    public Genetic(
            Crossover crossover,
            Mutation mutation,
            int populationSize,
            int numberOfGenerations
    ) {
        this.crossover = crossover;
        this.mutation = mutation;
        this.populationSize = populationSize;
        this.numberOfGenerations = numberOfGenerations;
    }

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException {
        run = true;
        distanceMatrix = matrix.getDistanceMatrix();

        // create population
        List<List<Integer>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<Integer> chromosome = new ArrayList<>();
            for (int j = 0; j < distanceMatrix.length; j++) {
                chromosome.add(j);
            }
            Collections.shuffle(chromosome);
            population.add(chromosome);
        }

        //mutate and crossover
        for (int i = 0; i < numberOfGenerations; i++) {
            if(!run) {
                break;
            }
            population = crossover.crossover(population);
            population = mutation.mutate(population);
            sortByFitness(population);
        }

        // The best tour is the first tour in the sorted population
        List<Integer> bestChromosome = population.get(0);

        return new AlgorithmResult(
            matrix.size,
            Arrays.stream(bestChromosome.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray(),
            calculateTotalDistance(bestChromosome),
            getName()
        );
    }

    private void sortByFitness(List<List<Integer>> population) {
        population.sort(
            (t1, t2) -> Double.compare(
                this.calculateFitness(t2),
                this.calculateFitness(t1)
            )
        );
    }

    private double calculateFitness(List<Integer> chromosome) {
        double totalCost = calculateTotalDistance(chromosome);

        return 1 / totalCost; // Maximizing fitness, so use reciprocal of cost.
    }

    private int calculateTotalDistance(List<Integer> tour) {
        int totalCost = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get(i + 1);
            totalCost += distanceMatrix[city1][city2];
        }
        totalCost += distanceMatrix[tour.get(tour.size() - 1)][tour.get(0)];
        return totalCost;
    }

    @Override
    public String getName() {
        return "Genetic Algorithm";
    }

    @Override
    public void stopExecution() {
        run = false;
    }

    @Override
    public void registerObserver(String name, AlgorithmObserver observer) {
        if(name.equals("BestSolutionObserver")) {
            this.observer = observer;
        }
    }
}
