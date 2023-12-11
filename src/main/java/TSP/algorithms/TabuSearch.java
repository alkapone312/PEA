package TSP.algorithms;

import TSP.data.Matrix;

public class TabuSearch implements Algorithm {
    private boolean run = true;

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) {
        run = true;



        return null;
    }

    @Override
    public String getName() {
        return "Tabu Search";
    }

    @Override
    public void stopExecution() {
        run = false;
    }
}
