package TSP.algorithms.BranchAndBound;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.algorithms.utils.AlgorithmObserver;
import TSP.data.Matrix;
import TSP.menu.TSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class BranchAndBound implements Algorithm {
    // Define the number of cities
    private int numberOfVertices;

    private int best;

    private boolean run;

    // Function to calculate the lower bound of the path starting from the specified node
    private int bound(int[][] cost, TSPBBNode buff) {
        int lowerBound = 0;
        boolean[] excludedRows = new boolean[numberOfVertices];
        boolean[] excludedCols = new boolean[numberOfVertices];
        while(buff.parent != null) {
            excludedRows[buff.parent.vertex] = true;
            excludedCols[buff.vertex] = true;
            lowerBound+=cost[buff.parent.vertex][buff.vertex];
            buff = buff.parent;
        }


        // Calculate the cost of the path so far
        for (int i = 0; i < numberOfVertices; i++) {
            if (!excludedRows[i]) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < numberOfVertices; j++) {
                    if (i != j && !excludedCols[j]) {
                        min = Math.min(min, cost[i][j]);
                    }
                }
                lowerBound += min;
            }
        }

        return lowerBound;
    }


    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) {
        run = true;
        int[][] costMatrix = matrix.getDistanceMatrix();
        numberOfVertices = matrix.size;
        PriorityQueue<TSPBBNode> priorityQueue = new PriorityQueue<>();

        TSPBBNode root = new TSPBBNode(0, 0, null);
        root.lowerBound = bound(costMatrix, root);
        priorityQueue.add(root);

        boolean[] visited;
        while (!priorityQueue.isEmpty() && run) {
            TSPBBNode currentNode = priorityQueue.poll();
            visited = getVisited(currentNode);
            // If all cities are visited, add the path cost and print the path
            if (currentNode.level == numberOfVertices - 1) {
                int[] bestTour = new int[numberOfVertices];
                TSPBBNode buff = currentNode;
                int i = numberOfVertices - 1;
                while(buff != null) {
                    bestTour[i] = buff.vertex;
                    buff = buff.parent;
                    i--;
                }
                return new AlgorithmResult(
                        numberOfVertices,
                        bestTour,
                        currentNode.cost + costMatrix[currentNode.vertex][0],
                        getName()
                );
            }

            // Branch out to the next level
            for (int i = 0; i < numberOfVertices; i++) {
                if (!visited[i]) {
                    TSPBBNode node = new TSPBBNode(
                            i,
                            currentNode.level + 1,
                            currentNode
                    );
                    node.cost = currentNode.cost + costMatrix[currentNode.vertex][i];
                    node.lowerBound = bound(costMatrix, node);

                    priorityQueue.add(node);
                }
            }
        }

        return new AlgorithmResult(numberOfVertices, new int[0], 0, getName());
    }

    private boolean[] getVisited(TSPBBNode node) {
        boolean[] visited = new boolean[numberOfVertices];
        TSPBBNode buff = node;
        while(buff != null) {
            visited[buff.vertex] = true;
            buff = buff.parent;
        }

        return visited;
    }

    @Override
    public String getName() {
        return "Branch and bound";
    }

    @Override
    public void stopExecution() {
        run = false;
    }

    @Override
    public void registerObserver(String name, AlgorithmObserver observer) {
    }
}
