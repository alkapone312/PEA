package TSP.implementations.forhuman;

import TSP.algorithms.*;
import TSP.algorithms.BranchAndBound.BranchAndBound;
import TSP.algorithms.Genetic.*;
import TSP.algorithms.utils.*;
import TSP.config.GeneralConfig;
import TSP.config.GeneticAlgorithmConfig;
import TSP.config.SimulatedAnnealingConfig;
import TSP.config.TabuSearchConfig;

import java.io.File;
import java.util.Scanner;

public class HumanAlgorithmProvider implements AlgorithmProvider {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Algorithm getAlgorithm() {
        Algorithm algorithm = null;
        while (true) {
            System.out.println("Wybierz algorytm");
            System.out.println("1. Zupełne");
            System.out.println("2. Niezupełne");
            System.out.println("3. Ustawienia");
            System.out.println("4. Wyjście");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: algorithm = complete();break;
                    case 2: algorithm = notComplete();break;
                    case 3: settings();break;
                    default: return null;
                }
            } catch (Exception e) {
                System.out.println("Wystąpił błąd! Spróbuj ponownie.");
                scanner = new Scanner(System.in);
            }

            if(algorithm != null) {
                return algorithm;
            }
        }
    }

    private Algorithm complete() {
        while (true) {
            System.out.println("Wybierz algorytm");
            System.out.println("1. BruteForce");
            System.out.println("2. Branch&Bound");
            System.out.println("3. Dynamic programming");
            System.out.println("4. Settings");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: return new BruteForce();
                case 2: return new BranchAndBound();
                case 3: return new DynamicProgramming();
                case 4: settings(); break;
                default: return null;
            }
        }
    }

    private Algorithm notComplete() {
        while (true) {
            System.out.println("Wybierz algorytm");
            System.out.println("1. Tabu Search");
            System.out.println("2. Symulowane wyżarzanie");
            System.out.println("3. Algorytm genetyczny");
            System.out.println("4. Ustawienia");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: return new TabuSearch(
                    TabuSearchConfig.neighborGeneration,
                    TabuSearchConfig.firstSolutionGeneration
                );
                case 2: return new SimulatedAnnealing(
                    SimulatedAnnealingConfig.neighborGeneration,
                    SimulatedAnnealingConfig.firstSolutionGeneration,
                    SimulatedAnnealingConfig.temperatureChange
                );
                case 3: return new Genetic(
                    GeneticAlgorithmConfig.crossover,
                    GeneticAlgorithmConfig.mutation,
                    GeneticAlgorithmConfig.selection,
                    GeneticAlgorithmConfig.population
                );
                case 4: settings(); break;
                default: return null;
            }
        }
    }

    private void settings() {
        while (true) {
            System.out.println("Wybierz ustawienia:");
            System.out.println("1. Wybierz plik (" + GeneralConfig.file + ")");
            System.out.println("2. Ustaw kryterium stopu (" + ((double)GeneralConfig.executionTimeMs/1000) + "s)");
            System.out.println("3. TabuSearch");
            System.out.println("4. Symulowane wyżarzanie");
            System.out.println("5. Algorytm genetyczny");
            System.out.println("6. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: readFile(); break;
                case 2: executionTime(); break;
                case 3: tabuSearch(); break;
                case 4: simulatedAnnealing(); break;
                case 5: geneticAlgorithm(); break;
                default: return;
            }
        }
    }

    private void readFile() {
        System.out.println("Podaj nazwe pliku:");
        String buff = scanner.nextLine();
        buff = scanner.nextLine();
        if(new File(buff).exists() && new File(buff).isFile()) {
            GeneralConfig.file = buff;
        } else {
            System.out.println("Nieprawidłowy plik!");
        }
    }

    private void executionTime() {
        GeneralConfig.executionTimeMs = getInt("Podaj maksymalny czas wykonywania algorytmu w sekundach: ") * 1000;
    }

    private void tabuSearch() {
        while (true) {
            System.out.println("Wybierz ustawienia:");
            System.out.println("1. Metoda generacji sąsiadów ("+getNameOfObjectClass(TabuSearchConfig.neighborGeneration)+")");
            System.out.println("2. Metoda generacji pierwszego rozwiązania ("+getNameOfObjectClass(TabuSearchConfig.firstSolutionGeneration)+")");
            System.out.println("3. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: TabuSearchConfig.neighborGeneration = getNeighborGeneration(); break;
                case 2: TabuSearchConfig.firstSolutionGeneration = getFirstSolutionGeneration(); break;
                default: return;
            }
        }
    }

    private void simulatedAnnealing() {
        while (true) {
            System.out.println("Wybierz ustawienia:");
            System.out.println("1. Metoda generacji sąsiadów ("+getNameOfObjectClass(SimulatedAnnealingConfig.neighborGeneration)+")");
            System.out.println("2. Metoda generacji pierwszego rozwiązania ("+getNameOfObjectClass(SimulatedAnnealingConfig.firstSolutionGeneration)+")");
            System.out.println("3. Współczynnik schładzania ("+SimulatedAnnealingConfig.temperatureChange+")");
            System.out.println("4. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: SimulatedAnnealingConfig.neighborGeneration = getNeighborGeneration(); break;
                case 2: SimulatedAnnealingConfig.firstSolutionGeneration = getFirstSolutionGeneration(); break;
                case 3: SimulatedAnnealingConfig.temperatureChange = getDouble("Podaj współczynnik schładzania:"); break;
                default: return;
            }
        }
    }

    private void geneticAlgorithm() {
        while (true) {
            System.out.println("Wybierz ustawienia:");
            System.out.println("1. Metoda krzyżowania ("+getNameOfObjectClass(GeneticAlgorithmConfig.crossover)+")");
            System.out.println("2. Metoda mutacji ("+getNameOfObjectClass(GeneticAlgorithmConfig.mutation)+")");
            System.out.println("3. Metoda selekcji ("+getNameOfObjectClass(GeneticAlgorithmConfig.selection)+")");
            System.out.println("4. Wielkość populacji ("+GeneticAlgorithmConfig.population+")");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: GeneticAlgorithmConfig.crossover = getCrossoverMethod(); break;
                case 2: GeneticAlgorithmConfig.mutation = getMutationMethod(); break;
                case 3: GeneticAlgorithmConfig.selection = getSelectionMethod(); break;
                case 4: GeneticAlgorithmConfig.population = getInt("Podaj liczbe osobników w populacji: "); break;
                default: return;
            }
        }
    }

    private Crossover getCrossoverMethod() {
        System.out.println("Wybierz metode krzyżowania");
        System.out.println("1. Ordered Crossover");
        System.out.println("2. Cycle Crossover");
        System.out.println("3. Wyjście");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: return new OrderedCrossover(getDouble("Podaj współczynnik krzyżowania"));
            case 2: return new CycleCrossover(getDouble("Podaj współczynnik krzyżowania"));
            default: return GeneticAlgorithmConfig.crossover;
        }
    }

    private Mutation getMutationMethod() {
        System.out.println("Wybierz metode mutacji:");
        System.out.println("1. Swap Mutation");
        System.out.println("2. Inverse Mutation");
        System.out.println("3. Wyjście");
        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> new SwapMutation(getDouble("Podaj współczynnik mutacji (0:1):"));
            case 2 -> new InversionMutation(getDouble("Podaj współczynnik mutacji (0:1):"));
            default -> GeneticAlgorithmConfig.mutation;
        };
    }

    private Selection getSelectionMethod() {
        System.out.println("Wybierz metode selekcji:");
        System.out.println("1. Tournament selection");
        scanner.nextLine();

        return new TournamentSelection();
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

    private int getInt(String message) {
        System.out.println(message);

        return scanner.nextInt();
    }

    private String getNameOfObjectClass(Object o) {
        return o.getClass().getSimpleName();
    }
}
