package TSP.implementations.forhuman;

import TSP.measurement.MeasuredAlghorithmResult;
import TSP.measurement.MeasuredAlgorithmResultWithBestSolutionTrack;

import java.util.HashMap;
import java.util.Set;

public class HumanReadableReportWithBestSolutionTrack extends HumanReadableReportGenerator {
    protected String buildOutputString(MeasuredAlghorithmResult result) {
        MeasuredAlgorithmResultWithBestSolutionTrack r = (MeasuredAlgorithmResultWithBestSolutionTrack) result;

        return generateBestSolutionUpdateMap(r) + super.buildOutputString(r);
    }

    private String generateBestSolutionUpdateMap(MeasuredAlgorithmResultWithBestSolutionTrack r) {
        HashMap<Long, Long> map = r.getTimeBestSolutionMap();

        StringBuilder s = new StringBuilder();
        Set<Long> keys = map.keySet();
        for (Long time : keys) {
            s.append(time).append(": ").append(map.get(time)).append("\n");
        }

        return s.toString();
    }
}
