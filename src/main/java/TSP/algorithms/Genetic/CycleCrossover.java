package TSP.algorithms.Genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CycleCrossover implements Crossover {
    private Random r = new Random();

    private double crossoverFactor;

    public CycleCrossover(double crossoverFactor) {
        this.crossoverFactor = crossoverFactor;
    }

    @Override
    public List<Tour> crossover(List<Tour> population) {
        List<Tour> newTours = new ArrayList<>();

        for (int i = 0; i < population.size() - 1; i += 2) {
            Tour parent1 = population.get(i);
            Tour parent2 = population.get(i + 1);

            // Check if crossover should be applied
            if (Math.random() < crossoverFactor) {
                // Perform Cycle Crossover
                Tour child1 = cycleCrossover(parent1, parent2);
                Tour child2 = cycleCrossover(parent2, parent1);

                newTours.add(child1);
                newTours.add(child2);
            } else {
                // If crossover is not applied, simply add the parents to the new population
                newTours.add(parent1);
                newTours.add(parent2);
            }
        }

        return newTours;
    }

    private Tour cycleCrossover(Tour parent1, Tour parent2) {
        int tourSize = parent1.getTour().size();
        int[] child = new int[tourSize];
        Arrays.fill(child, -1);

        int index = r.nextInt(tourSize);

        do {
            int currentCity = parent1.getTour().get(index);
            child[index] = currentCity;
            index = parent2.getTour().indexOf(currentCity);
        } while (child[index] == -1);

        for (int i = 0; i < tourSize; i++) {
            if (child[i] == -1) {
                child[i] = parent2.getTour().get(i);
            }
        }

        return new Tour(toList(child));
    }

    private int getRandomPosition(int maxValue) {
        return r.nextInt(maxValue);
    }

    private static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }
}
