package TSP.measurement;

import TSP.algorithms.AlgorithmResult;

import java.util.HashMap;

public class MeasuredAlgorithmResultWithBestSolutionTrack extends MeasuredAlghorithmResult {
    HashMap<Long, Long> timeBestSolutionMap;

    public MeasuredAlgorithmResultWithBestSolutionTrack(
            AlgorithmResult result,
            long executionTime,
            boolean algorithmStopped,
            HashMap<Long, Long> timeBestSolutionMap
    ) {
        super(result, executionTime, algorithmStopped);
        this.timeBestSolutionMap = timeBestSolutionMap;
    }

    public HashMap<Long, Long> getTimeBestSolutionMap() {
        return timeBestSolutionMap;
    }
}
