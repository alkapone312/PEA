package TSP.algorithms;

import TSP.algorithms.neighbors.NeighborGeneration;
import TSP.data.Matrix;

import java.util.*;

public class TabuSearch implements Algorithm {
    private final NeighborGeneration neighborGeneration;

    private boolean run = true;

    public TabuSearch(NeighborGeneration neighborGeneration) {
        this.neighborGeneration = neighborGeneration;
    }

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) {
        run = true;

        int[][] distanceMatrix = matrix.getDistanceMatrix();
        int numCities = distanceMatrix.length;

        List<Integer> bestSolution = getRandomSolution(numCities);

        // Inicjalizacja rozwiązania początkowego (np. losowa permutacja miast)
        List<Integer> currentSolution = getRandomSolution(numCities);

        // Inicjalizacja listy tabu
        List<List<Integer>> tabuList = new ArrayList<>();

        long iteration = 0;

        // Główna pętla algorytmu
        while (run) {
            // Wygenerowanie sąsiadów rozwiązania
            List<List<Integer>> neighbors = this.neighborGeneration.generateNeighbors(currentSolution, (int) (0.1 * numCities * numCities));

            // Wybór najlepszego sąsiada spośród tych, którzy nie są na liście tabu
            List<Integer> bestNeighbor = getBestNeighbor(neighbors, distanceMatrix, tabuList);

            // Zaktualizowanie rozwiązania
            currentSolution = bestNeighbor;
            long tabuSize = (int)(0.4 * numCities * numCities);

            if(calculateTotalDistance(currentSolution, distanceMatrix) < calculateTotalDistance(bestSolution, distanceMatrix)) {
                bestSolution = currentSolution;
            }

            // Dodanie ruchu do listy tabu
            tabuList.add(new ArrayList<>(bestNeighbor));
            if (tabuList.size() > tabuSize) {
                tabuList.remove(0);
            }

            // Ocena i wyświetlenie aktualnej trasy
            int totalDistance = calculateTotalDistance(currentSolution, distanceMatrix);
            System.out.println("Iteration " + iteration + ": Total Distance = " + totalDistance);
            iteration++;
        }

        return new AlgorithmResult(
                numCities,
                Arrays.stream(bestSolution.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray(),
                calculateTotalDistance(bestSolution, distanceMatrix),
                getName()
        );
    }

    private List<Integer> getRandomSolution(int numCities) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution);

        return solution;
    }

    private List<Integer> getBestNeighbor(List<List<Integer>> neighbors, int[][] distanceMatrix, List<List<Integer>> tabuList) {
        int bestDistance = Integer.MAX_VALUE;
        List<Integer> bestNeighbor = null;

        for (List<Integer> neighbor : neighbors) {
            int distance = calculateTotalDistance(neighbor, distanceMatrix);
            if (distance < bestDistance && !tabuList.contains(neighbor)) {
                bestDistance = distance;
                bestNeighbor = neighbor;
            }
        }

        if (bestNeighbor == null) {
            // Jeśli wszystkie sąsiednie ruchy są na liście tabu, wybierz dowolny
            bestNeighbor = neighbors.get(0);
        }

        return bestNeighbor;
    }

    private int calculateTotalDistance(List<Integer> solution, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < solution.size() - 1; i++) {
            totalDistance += distanceMatrix[solution.get(i)][solution.get(i + 1)];
        }
        // Dodaj odległość z ostatniego do pierwszego miasta (cykliczność trasy)
        totalDistance += distanceMatrix[solution.get(solution.size() - 1)][solution.get(0)];

        return totalDistance;
    }

    @Override
    public String getName() {
        return "Tabu Search: " + this.neighborGeneration.getName();
    }

    @Override
    public void stopExecution() {
        run = false;
    }
}
