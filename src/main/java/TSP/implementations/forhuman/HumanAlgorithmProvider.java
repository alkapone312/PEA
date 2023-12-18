package TSP.implementations.forhuman;

import TSP.algorithms.*;
import TSP.algorithms.BranchAndBound.BranchAndBound;
import TSP.algorithms.utils.*;

import java.util.Scanner;

public class HumanAlgorithmProvider implements AlgorithmProvider {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Algorithm getAlgorithm() {
        while (true) {
            System.out.println("Wybierz algorytm");
            System.out.println("1. BruteForce");
            System.out.println("2. Branch&Bound");
            System.out.println("3. Dynamic programming");
            System.out.println("4. Tabu Search");
            System.out.println("5. Symulowane wyżarzanie");
            System.out.println("6. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return new BruteForce();
                case 2:
                    return new BranchAndBound();
                case 3:
                    return new DynamicProgramming();
                case 4:
                    return new TabuSearch(getNeighborGeneration(), getFirstSolutionGeneration());
                case 5:
                    return new SimulatedAnnealing(
                            getNeighborGeneration(),
                            getFirstSolutionGeneration(),
                            getDouble("Podaj współczynnik schładzania:")
                    );
                case 6:
                    return null;
                default: System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }

    private NeighborGeneration getNeighborGeneration() {
        System.out.println("Wybierz metode generowania sąsiadów");
        System.out.println("1. Generacja przez zamianę losowych miast");
        System.out.println("2. Generacja przez wstawienie losowego miasta");
        return switch (scanner.nextInt()) {
            case 1 -> new SwapRandomNodesNeighbors();
            default -> new RandomNodeInsertion();
        };
    }

    private FirstSolutionGeneration getFirstSolutionGeneration() {
        System.out.println("Wybierz metode tworzenia rozwiązania początkowego");
        System.out.println("1. Metoda zachłanna");
        System.out.println("2. Metoda losowa");
        return switch (scanner.nextInt()) {
            case 1 -> new GreedyFirstSolutionGeneration();
            default -> new RandomFirstSolutionGeneration();
        };
    }

    private double getDouble(String message) {
        System.out.println(message);

        return scanner.nextDouble();
    }
}
