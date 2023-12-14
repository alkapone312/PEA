package TSP.algorithms.utils;

import TSP.data.Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomFirstSolutionGeneration implements FirstSolutionGeneration {

    @Override
    public List<Integer> generateSolution(Matrix matrix) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < matrix.size; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution);

        return solution;
    }
}
