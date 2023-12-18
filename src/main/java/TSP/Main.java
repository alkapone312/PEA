package TSP;

import TSP.algorithms.*;
import TSP.algorithms.BranchAndBound.BranchAndBound;
import TSP.algorithms.utils.*;
import TSP.file.ToFileWriter;
import TSP.implementations.automated.CSVReportGenerator;
import TSP.implementations.automated.RandomDataProvider;
import TSP.implementations.automated.SequenceFileDataProvider;
import TSP.implementations.automated.StubAlgorithmProvider;
import TSP.implementations.forhuman.*;
import TSP.measurement.AlgorithmMeasurement;
import TSP.measurement.AlgorithmMeasurementWithBestSolutionTrack;
import TSP.menu.TSP;
import TSP.utils.ReportGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Main.doAutomated();
//        Main.doHuman();
    }

    public static void doHuman() throws IOException {
        ReportGenerator reportGenerator = new HumanReadableReportGenerator();
        TSP tsp = new TSP(
                new HumanDataProvider(),
                new HumanAlgorithmProvider(),
                reportGenerator,
                new HumanAlgorithmMeasurement()
        );
        tsp.run();
        new ToFileWriter("report.log").write(reportGenerator.generateReport());
    }

    public static void doAutomated() throws IOException {
//        automaticBruteForceTest();
//        automaticDynamicProgrammingTest();
//        automaticBranchAndBoundTest();
        automaticTabuSearchTest(
                "dane/niezupelne/ftv47.atsp",
                120,
                "report-tabu-random-swap-47.log",
                new SwapRandomNodesNeighbors()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv170.atsp",
                240,
                "report-tabu-random-swap-170.log",
                new SwapRandomNodesNeighbors()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/rbg403.atsp",
                360,
                "report-tabu-random-swap-403.log",
                new SwapRandomNodesNeighbors()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv47.atsp",
                120,
                "report-random-insert-47.log",
                new RandomNodeInsertion()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv170.atsp",
                240,
                "report-random-insert-170.log",
                new RandomNodeInsertion()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/rbg403.atsp",
                360,
                "report-random-insert-403.log",
                new RandomNodeInsertion()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv47.atsp",
                120,
                "report-tabu-reverse-subroute-47.log",
                new InverseRandomSubrouteGeneration()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv170.atsp",
                240,
                "report-tabu-reverse-subroute-170.log",
                new InverseRandomSubrouteGeneration()
        );
        automaticTabuSearchTest(
                "dane/niezupelne/rbg403.atsp",
                360,
                "report-tabu-reverse-subroute-403.log",
                new InverseRandomSubrouteGeneration()
        );


        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv47.atsp",
                120,
                0.99,
                "report-sa-47-0.99.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv170.atsp",
                240,
                0.99,
                "report-sa-170-0.99.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/rbg403.atsp",
                360,
                0.99,
                "report-sa-403-0.99.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv47.atsp",
                120,
                0.999,
                "report-sa-47-0.999.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv170.atsp",
                240,
                0.999,
                "report-sa-170-0.999.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/rbg403.atsp",
                360,
                0.999,
                "report-sa-403-0.999.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv47.atsp",
                120,
                0.9999,
                "report-sa-47-0.9999.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/ftv170.atsp",
                240,
                0.9999,
                "report-sa-170-0.9999.log"
        );
        automaticSimulatedAnnealing(
                "dane/niezupelne/rbg403.atsp",
                360,
                0.9999,
                "report-sa-403-0.9999.log"
        );
    }

    public static void automaticBruteForceTest() throws IOException {
        ReportGenerator reportGenerator = new CSVReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new BruteForce());
        TSP tsp = new TSP(
                new RandomDataProvider(new int[]{
                        8,8,8,8,8,
                        9,9,9,9,9,
                        10,10,10,10,10,
                        11,11,11,11,11,
                        12,12,12,12,12,
                        13,13,13,13,13,
                        14,14,14,14,14
                }),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement(2*60*1000)
        );
        tsp.run();
        new ToFileWriter("report.csv").write(reportGenerator.generateReport());
    }

    public static void automaticDynamicProgrammingTest() throws IOException {
        ReportGenerator reportGenerator = new CSVReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new DynamicProgramming());
        TSP tsp = new TSP(
                new RandomDataProvider(new int[]{
                        20,20,20,20,20,20,20,20,20,20,
                        21,21,21,21,21,21,21,21,21,21,
                        22,22,22,22,22,22,22,22,22,22,
                        23,23,23,23,23,23,23,23,23,23,
                        24,24,24,24,24,24,24,24,24,24,
                        25,25,25,25,25,25,25,25,25,25,
                }),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement(2*60*1000)
        );
        tsp.run();
        new ToFileWriter("report-dp.csv").write(reportGenerator.generateReport());
    }

    public static void automaticBranchAndBoundTest() throws IOException {
        ReportGenerator reportGenerator = new CSVReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new BranchAndBound());
        TSP tsp = new TSP(
                new RandomDataProvider(new int[]{
                        10,10,10,10,10,10,10,10,10,10,
                        15,15,15,15,15,15,15,15,15,15,
                        20,20,20,20,20,20,20,20,20,20,
                        22,22,22,22,22,22,22,22,22,22,
                        24,24,24,24,24,24,24,24,24,24,
                        25,25,25,25,25,25,25,25,25,25,
                        27,27,27,27,27,27,27,27,27,27,
                        28,28,28,28,28,28,28,28,28,28,
                        29,29,29,29,29,29,29,29,29,29,
                        30,30,30,30,30,30,30,30,30,30,
                        31,31,31,31,31,31,31,31,31,31,
                        32,32,32,32,32,32,32,32,32,32,
                        33,33,33,33,33,33,33,33,33,33,
                        34,34,34,34,34,34,34,34,34,34,
                        35,35,35,35,35,35,35,35,35,35,
                        36,36,36,36,36,36,36,36,36,36
                }),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement(2*60*1000)
        );
        tsp.run();
        new ToFileWriter("report-bb-leastcost.csv").write(reportGenerator.generateReport());
    }

    public static void automaticTabuSearchTest(
            String filename,
            int maxSecondsLimit,
            String reportName,
            NeighborGeneration generation
    ) throws IOException {
        ReportGenerator reportGenerator = new HumanReadableReportWithBestSolutionTrack();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new TabuSearch(
                generation,
                new GreedyFirstSolutionGeneration()
        ));
        String[] files = new String[10];
        Arrays.fill(files, filename);
        TSP tsp = new TSP(
                new SequenceFileDataProvider(files),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurementWithBestSolutionTrack((long) maxSecondsLimit*1000)
        );
        tsp.run();
        new ToFileWriter(reportName).write(reportGenerator.generateReport());
    }

    public static void automaticSimulatedAnnealing(
            String filename,
            int maxSecondsLimit,
            double coolingFactor,
            String reportName
    ) throws IOException {
        ReportGenerator reportGenerator = new HumanReadableReportWithBestSolutionTrack();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new SimulatedAnnealing(
                new SwapRandomNodesNeighbors(),
                new GreedyFirstSolutionGeneration(),
                coolingFactor
        ));
        String[] files = new String[10];
        Arrays.fill(files, filename);
        TSP tsp = new TSP(
                new SequenceFileDataProvider(files),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurementWithBestSolutionTrack((long) maxSecondsLimit*1000)
        );
        tsp.run();
        new ToFileWriter(reportName).write(reportGenerator.generateReport());
    }
}
