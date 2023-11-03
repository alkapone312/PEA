package TSP.menu;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmProvider;
import TSP.data.DataProvider;
import TSP.measurement.AlgorithmMeasurement;
import TSP.data.Matrix;
import TSP.measurement.MeasuredAlghorithmResult;
import TSP.utils.ReportGenerator;

public class TSP {
    private final DataProvider dataProvider;
    private final AlgorithmProvider algorithmProvider;
    private final ReportGenerator reportGenerator;
    private final AlgorithmMeasurement algorithmMeasurement;

    public TSP(
            DataProvider dataProvider,
            AlgorithmProvider algorithmProvider,
            ReportGenerator reportGenerator,
            AlgorithmMeasurement algorithmMeasurement
    ) {
        this.dataProvider = dataProvider;
        this.algorithmProvider = algorithmProvider;
        this.reportGenerator = reportGenerator;
        this.algorithmMeasurement = algorithmMeasurement;
    }

    public void run() {
        Matrix data;
        MeasuredAlghorithmResult result;
        Algorithm algorithm;
        while ((algorithm = algorithmProvider.getAlgorithm()) != null) {
            while ((data = dataProvider.getData()) != null) {
                System.out.println("Running for " + data.size);
                try {
                    result = algorithmMeasurement.measureAlgorithm(algorithm, data);
                    reportGenerator.addToReport(result);
                    Runtime.getRuntime().gc();
                } catch (Exception ignored) {
                }
            }
        }
    }
}
