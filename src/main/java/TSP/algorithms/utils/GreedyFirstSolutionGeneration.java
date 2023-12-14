package TSP.algorithms.utils;

import TSP.data.Matrix;

import java.util.ArrayList;
import java.util.List;

public class GreedyFirstSolutionGeneration implements FirstSolutionGeneration {
    @Override
    public List<Integer> generateSolution(Matrix matrix) {
        ArrayList<Integer> solution = new ArrayList<>();
        int[][] costMatrix = matrix.getDistanceMatrix();

        solution.add(0);
        for(int i = 0 ; i < costMatrix.length - 1; i++) {
            int bestBranch = Integer.MAX_VALUE;
            int bestIndex = -1;
            for (int j = 0; j < costMatrix.length; j++) {
                if (!solution.contains(j) && bestBranch > costMatrix[solution.get(solution.size() - 1)][j]) {
                    bestBranch = costMatrix[solution.get(solution.size() - 1)][j];
                    bestIndex = j;
                }
            }
            solution.add(bestIndex);
        }

        return solution;
    }
}
