package TSP.measurement;

import TSP.algorithms.AlgorithmResult;

public class MeasuredAlghorithmResult {
    /**
     * Basic result of an algorithm
     */
    private final AlgorithmResult result;

    /**
     * Execution time of an algohrithm
     */
    private final long executionTime;

    /**
     * Flag that checks wheater alghorithm succeded generating result
     */
    private final boolean algorithmStopped;

    public MeasuredAlghorithmResult(
            AlgorithmResult result,
            long executionTime,
            boolean algorithmStopped
    ) {
        this.result = result;
        this.executionTime = executionTime;
        this.algorithmStopped = algorithmStopped;
    }

    public AlgorithmResult getAlgorithmResult() {
        return result;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public boolean wasAlgorithmStopped() {
        return algorithmStopped;
    }
}
