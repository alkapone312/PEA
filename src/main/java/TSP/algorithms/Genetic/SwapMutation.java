package TSP.algorithms.Genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwapMutation implements Mutation {

    private double mutationFactor;

    public SwapMutation(double mutationFactor) {
        this.mutationFactor = mutationFactor;
    }

    @Override
    public List<List<Integer>> mutate(List<List<Integer>> population) {
        List<List<Integer>> mutatedPopulation = new ArrayList<>(population);
        for (List<Integer> tour : mutatedPopulation) {
            if (Math.random() < mutationFactor) {
                int tourSize = tour.size();
                int pos1 = getRandomPosition(tourSize);
                int pos2 = getRandomPosition(tourSize);

                // Ensure pos1 is different from pos2
                while (pos1 == pos2) {
                    pos2 = getRandomPosition(tourSize);
                }

                // Swap the cities at pos1 and pos2
                Collections.swap(tour, pos1, pos2);
            }
        }

        return mutatedPopulation;
    }

    private static int getRandomPosition(int maxValue) {
        return new Random().nextInt(maxValue);
    }
}
