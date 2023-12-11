package TSP.algorithms;

import TSP.data.Matrix;

import java.util.Arrays;

public class DynamicProgramming implements Algorithm {

    private boolean run;
    private int numberOfVertices;
    private int[][] costMatrix;

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException {
        run = true;
        numberOfVertices = matrix.size;
        costMatrix = matrix.getDistanceMatrix();

        int[][] dp = new int[1 << numberOfVertices][numberOfVertices];
        int[][] paths = new int[1 << numberOfVertices][numberOfVertices];
        for (int i = 0 ; i < 1 << numberOfVertices; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(paths[i], -1);
        }
        int shortestPathCost = tsp(1, 0, dp, paths);

        if(!run) {
            return new AlgorithmResult(matrix.getDistanceMatrix().length, new int[]{}, 0, getName());
        }

        int mask = 1;
        int[] path = new int[numberOfVertices + 1];
        path[0] = 0;
        for(int i = 0 ; i < numberOfVertices; i++) {
            int minIndex = 0;
            int minValue = Integer.MAX_VALUE;
            for(int j = 0 ; j < dp[mask].length; j++) {
                int v = dp[mask][j];
                if(v != -1 && v < minValue) {
                    minIndex = j;
                    minValue = v;
                }
            }
            path[i + 1] = paths[mask][minIndex];
            mask = mask | (1 << paths[mask][minIndex]);
        }

        return new AlgorithmResult(numberOfVertices, path, shortestPathCost, getName());
    }

    private int tsp(int mask, int pos, int[][] dp, int[][] path) {
        if(!run) {
            return -1;
        }

        if (mask == (1 << numberOfVertices) - 1) {
            path[mask][pos] = 0;
            return dp[mask][pos] = costMatrix[pos][0];
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfVertices; i++) {
            if ((mask & (1 << i)) == 0) {
                int newMask = mask | (1 << i);
                int cost = costMatrix[pos][i] + tsp(newMask, i, dp, path);
                if (cost < ans) {
                    path[mask][pos] = i;
                    ans = cost;
                }
            }
        }

        dp[mask][pos] = ans;

        return ans;
    }

    @Override
    public String getName() {
        return "Dynamic Programming";
    }

    @Override
    public void stopExecution() {
        run = false;
    }
}
