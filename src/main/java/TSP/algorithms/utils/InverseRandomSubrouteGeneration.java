package TSP.algorithms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InverseRandomSubrouteGeneration implements NeighborGeneration {
    @Override
    public List<List<Integer>> generateNeighbors(List<Integer> solution, int numberOfNeightbors) {
        Random r = new Random();
        List<List<Integer>> neighbours = new ArrayList<>();

        for(int i = 0 ; i < numberOfNeightbors; i++) {
            List<Integer> neighbour = new ArrayList<>(solution);
            int a = 0;
            int b = 0;
            while (a == b) {
                a = r.nextInt(solution.size());
                b = r.nextInt(solution.size());
            }

            if (a > b) {
                int buff = b;
                b = a;
                a = buff;
            }

            while (a < b) {
                Collections.swap(neighbour, a, b);
                a++;
                b--;
            }

            neighbours.add(neighbour);
        }

        return neighbours;
    }

    @Override
    public String getName() {
        return "Odwrócenie losowej ścieżki";
    }
}
