package TSP.implementations.forhuman;

import TSP.measurement.MeasuredAlghorithmResult;
import TSP.utils.ReportGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class HumanReadableReportGenerator implements ReportGenerator {
    private ArrayList<MeasuredAlghorithmResult> results = new ArrayList<>();

    @Override
    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        for (MeasuredAlghorithmResult result : results) {
            builder.append(buildOutputString(result));
        }
        results = new ArrayList<>();

        return builder.toString();
    }

    public void addToReport(MeasuredAlghorithmResult result) {
        System.out.println(buildOutputString(result));
        results.add(result);
    }

    private String buildOutputString(MeasuredAlghorithmResult result) {
        return "Algorytm: " +
                result.getAlgorithmResult().getAlgorithmName() +
                "\n" +
                "Ilość wierzchołków: " +
                result.getAlgorithmResult().getNumberOfVertices() +
                "\n" +
                "Najkrótsza trasa: " +
                Arrays.toString(result.getAlgorithmResult().getBestTour()) +
                "\n" +
                "Długość trasy: " +
                result.getAlgorithmResult().getMinCost() +
                "\n" +
                "Czas wykonania: " +
                result.getExecutionTime() +
                " ms" +
                "\n" +
                "\n";
    }
}
