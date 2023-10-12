package TSP.implementations.forhuman;

import TSP.algorithms.Algorithm;
import TSP.algorithms.IncorrectDataException;
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
        if(getUpperTimeLimit() == 0) {
            setUpperTimeLimit(0);
        }

        return super.measureAlgorithm(algorithm, data);
    }

    @Override
    public void setUpperTimeLimit(long upperTimeLimit) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać czas zakończenia algorytmu w ms");
        super.setUpperTimeLimit(scanner.nextLong());
    }
}
