package TSP.algorithms.Genetic;

import java.util.List;

public interface Selection {
    Tour select(List<Tour> population);
}
