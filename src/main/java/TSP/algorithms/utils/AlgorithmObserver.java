package TSP.algorithms.utils;

import TSP.measurement.TimerException;

import java.util.List;

public interface AlgorithmObserver {
    void invoke(List<Integer> currentSolution, int currentCost);
}
