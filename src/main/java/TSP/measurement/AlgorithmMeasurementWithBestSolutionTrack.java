package TSP.measurement;

import TSP.algorithms.Algorithm;
import TSP.algorithms.IncorrectDataException;
import TSP.data.Matrix;

import java.util.HashMap;
import java.util.List;

public class AlgorithmMeasurementWithBestSolutionTrack extends AlgorithmMeasurement {
    public AlgorithmMeasurementWithBestSolutionTrack(long upperTimeLimit) {
        super(upperTimeLimit);
    }

    @Override
    public MeasuredAlghorithmResult measureAlgorithm(Algorithm algorithm, Matrix data) throws IncorrectDataException, TimerException {
        HashMap<Long, Long> bestSolutiomMap = new HashMap<>();
        algorithm.registerObserver("BestSolutionObserver", (List<Integer> solution, int cost) -> {
            try {
                bestSolutiomMap.put(timer.getElapsedTime(), (long) cost);
            } catch (Exception ignored) {}
        });

        MeasuredAlghorithmResult result = super.measureAlgorithm(algorithm, data);

        return new MeasuredAlgorithmResultWithBestSolutionTrack(
            result.getAlgorithmResult(),
            result.getExecutionTime(),
            result.wasAlgorithmStopped(),
            bestSolutiomMap
        );
    }
}
