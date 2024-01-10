package TSP.algorithms.Genetic;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.algorithms.utils.AlgorithmObserver;
import TSP.data.Matrix;

import java.util.*;

public class Genetic implements Algorithm {
    private AlgorithmObserver observer;
    private boolean run = true;
    private int[][] distanceMatrix;

    private Crossover crossover;
    private Mutation mutation;
    private Selection selection;
    private double populationSize;

    private Tour bestSolution = null;

    public Genetic(
            Crossover crossover,
            Mutation mutation,
            Selection selection,
            int populationSize
    ) {
        this.crossover = crossover;
        this.mutation = mutation;
        this.selection = selection;
        this.populationSize = populationSize;
    }

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException {
        run = true;
        distanceMatrix = matrix.getDistanceMatrix();

        // create population
        List<Tour> population = createPopulation();
        bestSolution = getBestTour(population);

        //mutate and crossover
        while (run) {
            population = createNewPopulation(population);
            population = crossover.crossover(population);
            population = mutation.mutate(population);
            recalculateDistances(population);

            Tour bestTour = getBestTour(population);
            if(bestSolution.getCost() > bestTour.getCost()) {
                bestSolution = bestTour.clone();
                if (observer != null) {
                    observer.invoke(bestSolution.getTour(), bestSolution.getCost());
                }
            }
        }

        return new AlgorithmResult(
            matrix.size,
            Arrays.stream(bestSolution.getTour().toArray(new Integer[0])).mapToInt(Integer::intValue).toArray(),
            bestSolution.getCost(),
            getName()
        );
    }

    private List<Tour> createPopulation() {
        List<Tour> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<Integer> chromosome = new ArrayList<>();
            for (int j = 0; j < distanceMatrix.length; j++) {
                chromosome.add(j);
            }
            Collections.shuffle(chromosome);
            population.add(new Tour(chromosome, calculateTotalDistance(chromosome)));
        }

        return population;
    }

    private List<Tour> createNewPopulation(List<Tour> population) {
        List<Tour> newPopulation = new ArrayList<>();
        for(int i = 0; i < population.size(); i++) {
            newPopulation.add(selection.select(population));
        }

        return newPopulation;
    }

    private void recalculateDistances(List<Tour> population) {
        for(Tour tour : population) {
            tour.setCost(calculateTotalDistance(tour.getTour()));
        }
    }

    private Tour getBestTour(List<Tour> population) {
        return Collections.min(population, Comparator.comparingInt(Tour::getCost));
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
