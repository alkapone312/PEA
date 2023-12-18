package TSP.measurement;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.data.Matrix;

public class AlgorithmMeasurement {
    protected long upperTimeLimit;

    public AlgorithmMeasurement(long upperTimeLimit) {
        this.upperTimeLimit = upperTimeLimit;
    }

    /**
     * Timer to be used to count time execution of an algorithm
     */
    protected final AlgorithmTimer timer = new AlgorithmTimer();

    public MeasuredAlghorithmResult measureAlgorithm(Algorithm algorithm, Matrix data) throws IncorrectDataException, TimerException {
        timer.start();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(upperTimeLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            algorithm.stopExecution();
        });
        t.start();
        AlgorithmResult result = algorithm.runAlgorithm(data);
        t.stop();

        return new MeasuredAlghorithmResult(
                result,
                timer.getElapsedTime(),
                timer.getElapsedTime() < upperTimeLimit
        );
    }

    public void setUpperTimeLimit(long upperTimeLimit) {
        this.upperTimeLimit = upperTimeLimit;
    }

    public long getUpperTimeLimit() {
        return upperTimeLimit;
    }
}
