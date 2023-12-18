package TSP.algorithms;

import TSP.algorithms.utils.AlgorithmObserver;
import TSP.data.Matrix;

public interface Algorithm {
    /**
     * Run algorithm on Matrix
     */
    AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException;

    /**
     * Get name of an algorithm
     *
     */
    String getName();

    /**
     * Stop the execution of an algorithm
     */
    void stopExecution();

    /**
     * Add observer that will watch for some values
     */
    void registerObserver(String name, AlgorithmObserver observer);
}
