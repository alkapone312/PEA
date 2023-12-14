package TSP.algorithms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapRandomNodesNeighbors implements NeighborGeneration {
    @Override
    public List<List<Integer>> generateNeighbors(List<Integer> solution, int numberOfNeightbors) {
        List<List<Integer>> neighbors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfNeightbors; i++) {
            int index1 = random.nextInt(solution.size());
            int index2 = random.nextInt(solution.size());

            List<Integer> neighbor = new ArrayList<>(solution);
            Collections.swap(neighbor, index1, index2);

            neighbors.add(neighbor);
        }

        return neighbors;
    }

    @Override
    public String getName() {
        return "Zamiana losowych miast";
    }
}
