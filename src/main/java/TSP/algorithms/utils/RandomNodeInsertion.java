package TSP.algorithms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNodeInsertion implements NeighborGeneration {
    @Override
    public List<List<Integer>> generateNeighbors(List<Integer> solution, int numberOfNeightbors) {
        Random r = new Random();
        List<List<Integer>> neighbours = new ArrayList<>();
        int size = solution.size();
        for(int i = 0 ; i < numberOfNeightbors; i++) {
            ArrayList<Integer> neighbour = new ArrayList<>(solution);
            int removed = neighbour.remove(r.nextInt(size));
            neighbour.add(r.nextInt(size), removed);
            neighbours.add(neighbour);
        }

        return neighbours;
    }

    @Override
    public String getName() {
        return "Insercja losowego miasta";
    }
}
