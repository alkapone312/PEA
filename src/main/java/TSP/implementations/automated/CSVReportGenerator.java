package TSP.implementations.automated;

import TSP.algorithms.AlgorithmResult;
import TSP.measurement.MeasuredAlghorithmResult;
import TSP.utils.ReportGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVReportGenerator implements ReportGenerator {
    private final HashMap<Integer, Long> sumExecutionTime = new HashMap<>();
    private final HashMap<Integer, Integer> numberOfIncorrect = new HashMap<>();
    private final HashMap<Integer, Integer> numberOfResults = new HashMap<>();
    private final ArrayList<MeasuredAlghorithmResult> results = new ArrayList<>();

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Liczba wierzchołków;Średni czas;% niepowodzeń\n");
        for (MeasuredAlghorithmResult result : results) {
            AlgorithmResult algorithmResult = result.getAlgorithmResult();
            int numberOfVertices = algorithmResult.getNumberOfVertices();
            if(sumExecutionTime.containsKey(numberOfVertices)) {
                sumExecutionTime.replace(numberOfVertices, sumExecutionTime.get(numberOfVertices) + result.getExecutionTime());
                numberOfResults.replace(numberOfVertices, numberOfResults.get(numberOfVertices) + 1);
                numberOfIncorrect.replace(
                        numberOfVertices,
                        numberOfIncorrect.get(numberOfVertices) + (result.wasAlgorithmStopped() ? 0 : 1)
                );
            } else {
                sumExecutionTime.put(numberOfVertices, result.getExecutionTime());
                numberOfResults.put(numberOfVertices, 1);
                numberOfIncorrect.put(numberOfVertices, result.wasAlgorithmStopped() ? 0 : 1);
            }
        }

        for (int nOfVertices : sumExecutionTime.keySet()) {
            long meanExecutionTime = sumExecutionTime.get(nOfVertices)/numberOfResults.get(nOfVertices);
            float percentOfIncorrect = (float)numberOfIncorrect.get(nOfVertices)/numberOfResults.get(nOfVertices)*100;
                report.append(nOfVertices);
                report.append(';');
                report.append(meanExecutionTime);
                report.append(';');
                report.append(percentOfIncorrect);
                report.append('\n');
        }

        return report.toString();
    }

    @Override
    public void addToReport(MeasuredAlghorithmResult result) {
        results.add(result);
    }
}
