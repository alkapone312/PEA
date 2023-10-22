package TSP.algorithms;

import TSP.data.Matrix;

import java.util.Arrays;

public class BruteForce implements Algorithm {

    private boolean run = true;

    public AlgorithmResult runAlgorithm(Matrix data) throws IncorrectDataException {
        run = true;
        if (data == null) {
            throw new IncorrectDataException("Najpierw wczytaj lub wygeneruj dane.");
        } else {
            int numCities = data.getDistanceMatrix().length;
            int[] bestTour = null;
            int minDistance = Integer.MAX_VALUE;

            int[] tour = new int[numCities];
            for (int i = 0; i < numCities; i++) {
                tour[i] = i;
            }

            do {
                int currentDistance = calculateTourDistance(tour, data, minDistance);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestTour = Arrays.copyOf(tour, numCities);
                }
            } while (run && nextPermutation(tour));
            int[] tourWithStart = Arrays.copyOf(bestTour, numCities + 1);
            tourWithStart[numCities] = tourWithStart[0];
            bestTour = tourWithStart;
            return new AlgorithmResult(numCities, bestTour, minDistance, getName());
        }
    }

    @Override
    public String getName() {
        return "Przegląd zupełny";
    }

    @Override
    public void stopExecution() {
        this.run = false;
    }

    private int calculateTourDistance(int[] tour, Matrix data, int minDistance) {
        int distance = 0;
        int[][] matrix = data.getDistanceMatrix();
        for (int i = 0; i < tour.length - 1; i++) {
            if(minDistance<distance){
                return distance;
            }
            distance += matrix[tour[i]][tour[i + 1]];
        }
        distance += matrix[tour[tour.length - 1]][tour[0]];
        return distance;
    }

    private boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] > array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] < array[i]) {
            j--;
        }

        swap(array, i, j);
        reverse(array, i + 1);
        return true;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void reverse(int[] array, int start) {
        int end = array.length - 1;
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
