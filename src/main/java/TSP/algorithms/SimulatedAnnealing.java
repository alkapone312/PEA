package TSP.algorithms;

import TSP.algorithms.utils.AlgorithmObserver;
import TSP.algorithms.utils.FirstSolutionGeneration;
import TSP.algorithms.utils.NeighborGeneration;
import TSP.data.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulatedAnnealing implements Algorithm {

    private final FirstSolutionGeneration firstSolutionGeneration;

    private NeighborGeneration neighborGeneration;

    private AlgorithmObserver observer;

    private double temperatureChange;

    private boolean run = true;

    public SimulatedAnnealing(
            NeighborGeneration neighborGeneration,
            FirstSolutionGeneration firstSolutionGeneration,
            double temperatureChange
    ) {
        this.firstSolutionGeneration = firstSolutionGeneration;
        this.neighborGeneration = neighborGeneration;
        this.temperatureChange = temperatureChange;
    }

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException {
        run = true;

        List<Integer> currentSolution = firstSolutionGeneration.generateSolution(matrix);
        List<Integer> bestSolution = new ArrayList<>(currentSolution);
        int bestSolutionCost = Integer.MAX_VALUE;

        // Algorytm symulowanego wyżarzania
        int ITERATIONS_PER_TEMPERATURE = 1000;
        double temperature = calculateInitialTemperature(currentSolution, matrix.getDistanceMatrix());

        while (run) {
            for (int i = 0; i < ITERATIONS_PER_TEMPERATURE; i++) {
                List<Integer> newSolution = neighborGeneration
                        .generateNeighbors(currentSolution, 1)
                        .get(0);

                // Obliczanie kosztu dla obu tras
                int currentCost = calculateTotalDistance(currentSolution, matrix.getDistanceMatrix());
                int newCost = calculateTotalDistance(newSolution, matrix.getDistanceMatrix());

                // Akceptacja nowej trasy z pewnym prawdopodobieństwem
                if (acceptanceProbability(currentCost, newCost, temperature) > Math.random()) {
                    currentSolution = newSolution;
                    if(newCost < bestSolutionCost) {
                        bestSolution = currentSolution;
                        bestSolutionCost = newCost;
                        observer.invoke(currentSolution, bestSolutionCost);
                    }
                }
            }

            temperature *= temperatureChange;
        }

        return new AlgorithmResult(
                matrix.size,
                Arrays.stream(bestSolution.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray(),
                bestSolutionCost,
                getName()
        );
    }

    private int calculateTotalDistance(List<Integer> solution, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < solution.size() - 1; i++) {
            int city1 = solution.get(i);
            int city2 = solution.get(i + 1);
            totalDistance += distanceMatrix[city1][city2];
        }
        // Add distance from the last city to the first city
        totalDistance += distanceMatrix[solution.get(solution.size() - 1)][solution.get(0)];
        return totalDistance;
    }

    private double acceptanceProbability(double currentCost, double newCost, double temperature) {
        if (newCost < currentCost) {
            return 1.0; // Always accept a better route
        }
        return Math.exp((currentCost - newCost) / temperature);
    }

    private double calculateInitialTemperature(List<Integer> initialSolution, int[][] distanceMatrix) {
        int numSteps = 1000;
        double totalDeltaCost = 0.0;

        for (int i = 0; i < numSteps; i++) {
            List<Integer> newSolution = neighborGeneration
                    .generateNeighbors(initialSolution, 1)
                    .get(0);

            double currentCost = calculateTotalDistance(initialSolution, distanceMatrix);
            double newCost = calculateTotalDistance(newSolution, distanceMatrix);
            totalDeltaCost += Math.abs(newCost - currentCost);
        }

        double averageDeltaCost = totalDeltaCost / numSteps;
        return -averageDeltaCost / Math.log(0.99);
    }

    @Override
    public String getName() {
        return "Symulowane wyżarzanie: " + neighborGeneration.getName();
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
