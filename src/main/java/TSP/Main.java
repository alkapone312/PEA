package TSP;

import TSP.algorithms.Algorithm;
import TSP.algorithms.BranchAndBound.BranchAndBound;
import TSP.algorithms.BruteForce;
import TSP.algorithms.DynamicProgramming;
import TSP.algorithms.TabuSearch;
import TSP.algorithms.utils.GreedyFirstSolutionGeneration;
import TSP.algorithms.utils.InverseRandomSubrouteGeneration;
import TSP.algorithms.utils.RandomNodeInsertion;
import TSP.algorithms.utils.SwapRandomNodesNeighbors;
import TSP.file.ToFileWriter;
import TSP.implementations.automated.CSVReportGenerator;
import TSP.implementations.automated.RandomDataProvider;
import TSP.implementations.automated.SequenceFileDataProvider;
import TSP.implementations.automated.StubAlgorithmProvider;
import TSP.implementations.forhuman.HumanAlgorithmMeasurement;
import TSP.implementations.forhuman.HumanAlgorithmProvider;
import TSP.implementations.forhuman.HumanDataProvider;
import TSP.implementations.forhuman.HumanReadableReportGenerator;
import TSP.measurement.AlgorithmMeasurement;
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
                "report-tabu-reverse-subroute-47.log"
        );
        automaticTabuSearchTest(
                "dane/niezupelne/ftv170.atsp",
                240,
                "report-tabu-reverse-subroute-170.log"
        );
        automaticTabuSearchTest(
                "dane/niezupelne/rbg403.atsp",
                360,
                "report-tabu-reverse-subroute-403.log"
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
            String reportName
    ) throws IOException {
        ReportGenerator reportGenerator = new HumanReadableReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new TabuSearch(
                new InverseRandomSubrouteGeneration(),
                new GreedyFirstSolutionGeneration()
        ));
        String[] files = new String[10];
        Arrays.fill(files, filename);
        TSP tsp = new TSP(
                new SequenceFileDataProvider(files),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement((long) maxSecondsLimit*1000)
        );
        tsp.run();
        new ToFileWriter(reportName).write(reportGenerator.generateReport());
    }
}
