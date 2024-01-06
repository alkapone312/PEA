package TSP.algorithms.Genetic;

import java.util.ArrayList;
import java.util.List;

public class Tour {
    private List<Integer> tour;

    private int cost = -1;

    public Tour(List<Integer> tour) {
        this.tour = tour;
    }

    public Tour(List<Integer> tour, int cost) {
        this.tour = tour;
        this.cost = cost;
    }

    public List<Integer> getTour() {
        return tour;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Tour clone() {
        return new Tour(new ArrayList<>(getTour()), getCost());
    }
}
