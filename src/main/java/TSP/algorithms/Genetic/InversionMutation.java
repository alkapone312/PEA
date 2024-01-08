package TSP.algorithms.Genetic;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InversionMutation implements Mutation {
    private Random r = new Random();

    private double mutationFactor;

    public InversionMutation(double mutationFactor) {
        this.mutationFactor = mutationFactor;
    }

    @Override
    public List<Tour> mutate(List<Tour> population) {
        for (Tour tour : population) {
            if (r.nextDouble() < mutationFactor) {
                inversionMutate(tour);
            }
        }

        return population;
    }

    private void inversionMutate(Tour tour) {
        int tourSize = tour.getTour().size();
        int startPos = getRandomPosition(tourSize);
        int endPos = getRandomPosition(tourSize);

        // Ensure startPos is less than endPos
        if (startPos > endPos) {
            int temp = startPos;
            startPos = endPos;
            endPos = temp;
        }

        // Invert the order of cities between startPos and endPos
        while (startPos < endPos) {
            Collections.swap(tour.getTour(), startPos, endPos);
            startPos++;
            endPos--;
        }
    }

    private int getRandomPosition(int maxValue) {
        return r.nextInt(maxValue);
    }
}
