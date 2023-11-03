package TSP.algorithms.BranchAndBound;

public class TSPBBNode implements Comparable<TSPBBNode>{
    public int vertex;
    public int level;
    public int cost;
    public int lowerBound;
    public TSPBBNode parent;

    public TSPBBNode(int vertex, int level, TSPBBNode parent) {
        this.vertex = vertex;
        this.level = level;
        this.parent = parent;
        this.cost = 0;
        this.lowerBound = 0;
    }

    @Override
    public int compareTo(TSPBBNode node) {
        return this.lowerBound - node.lowerBound;
    }
}
