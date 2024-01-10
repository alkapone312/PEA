package TSP.implementations.forhuman;

import TSP.algorithms.Algorithm;
import TSP.algorithms.IncorrectDataException;
import TSP.config.GeneralConfig;
import TSP.data.Matrix;
import TSP.measurement.AlgorithmMeasurement;
import TSP.measurement.MeasuredAlghorithmResult;
import TSP.measurement.TimerException;

import java.util.Scanner;

public class HumanAlgorithmMeasurement extends AlgorithmMeasurement {
    public HumanAlgorithmMeasurement() {
        super(0);
    }

    @Override
    public MeasuredAlghorithmResult measureAlgorithm(Algorithm algorithm, Matrix data) throws IncorrectDataException, TimerException {
        setUpperTimeLimit(GeneralConfig.executionTimeMs);
        return super.measureAlgorithm(algorithm, data);
    }
}
