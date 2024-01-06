package TSP.algorithms.Genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderedCrossover implements Crossover {
    private double crossoverFactor;

    public OrderedCrossover(double crossoverFactor) {
        this.crossoverFactor = crossoverFactor;
    }

    @Override
    public List<Tour> crossover(List<Tour> population) {
        List<Tour> crossedPopulation = new ArrayList<>();

        for (int i = 0; i < population.size() - 1; i += 2) {
            List<Integer> parent1 = population.get(i).getTour();
            List<Integer> parent2 = population.get(i + 1).getTour();

            // Perform Ordered Crossover
            List<Integer> child1 = orderedCrossover(parent1, parent2);
            List<Integer> child2 = orderedCrossover(parent2, parent1);

            crossedPopulation.add(new Tour(child1));
            crossedPopulation.add(new Tour(child2));
        }

        // Replace the old population with the new one
        return crossedPopulation;
    }

    private List<Integer> orderedCrossover(List<Integer> parent1, List<Integer> parent2) {
        int tourSize = parent1.size();
        int[] child = new int[tourSize];
        int startPos = getRandomPosition(tourSize);
        int endPos = getRandomPosition(tourSize);

        // Ensure startPos is less than endPos
        if (startPos > endPos) {
            int temp = startPos;
            startPos = endPos;
            endPos = temp;
        }

        // Copy a segment from parent1 to child
        for (int i = startPos; i <= endPos; i++) {
            child[i] = parent1.get(i);
        }

        // Fill the remaining positions with genes from parent2
        int parent2Index = 0;
        for (int i = 0; i < tourSize; i++) {
            if (!contains(child, parent2.get(i))) {
                while (child[parent2Index] != 0) {
                    parent2Index++;
                }
                child[parent2Index++] = parent2.get(i);
            }
        }

        return new ArrayList<>(toList(child));
    }

    private static int getRandomPosition(int maxValue) {
        return new Random().nextInt(maxValue);
    }

    private static boolean contains(int[] array, int value) {
        for (int element : array) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    private static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }
}
