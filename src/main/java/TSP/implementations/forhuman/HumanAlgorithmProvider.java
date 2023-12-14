package TSP.implementations.forhuman;

import TSP.algorithms.*;
import TSP.algorithms.BranchAndBound.BranchAndBound;
import TSP.algorithms.utils.*;

import java.util.Scanner;

public class HumanAlgorithmProvider implements AlgorithmProvider {
    @Override
    public Algorithm getAlgorithm() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Wybierz algorytm");
            System.out.println("1. BruteForce");
            System.out.println("2. Branch&Bound");
            System.out.println("3. Dynamic programming");
            System.out.println("4. Tabu Search");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return new BruteForce();
                case 2:
                    return new BranchAndBound();
                case 3:
                    return new DynamicProgramming();
                case 4:
                    System.out.println("Wybierz metode generowania sąsiadów");
                    System.out.println("1. Generacja przez zamianę losowych miast");
                    System.out.println("2. Generacja przez wstawienie losowego miasta");
                    NeighborGeneration neighborGeneration = switch (scanner.nextInt()) {
                        case 1 -> new SwapRandomNodesNeighbors();
                        default -> new RandomNodeInsertion();
                    };
                    System.out.println("Wybierz metode tworzenia rozwiązania początkowego");
                    System.out.println("1. Metoda zachłanna");
                    System.out.println("2. Metoda losowa");
                    FirstSolutionGeneration firstSolutionGeneration = switch (scanner.nextInt()) {
                        case 1 -> new GreedyFirstSolutionGeneration();
                        default -> new RandomFirstSolutionGeneration();
                    };
                    return new TabuSearch(neighborGeneration, firstSolutionGeneration);
                case 5:
                    return null;
                default: System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }
}
