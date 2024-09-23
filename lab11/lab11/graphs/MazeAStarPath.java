package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Node> queue;

    private class Node implements Comparable<Node> {
        private int source;
        private int estimateDis;
        private int weight;

        public Node(int source, int estimateDis) {
            this.source = source;
            this.estimateDis = estimateDis;
//            this.weight = source + estimateDis;
            this.weight = estimateDis;
        }

        public int getSource() {
            return source;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.getWeight();
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        this.queue = new MinPQ<>();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x = maze.toX(v);
        int y = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);

        return Math.abs(x - targetX) + Math.abs(y - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        marked[s] = true;
        queue.insert(new Node(s, h(s)));
        announce();
        if (s == t) {
            targetFound = true;
            return;
        }
        while (!queue.isEmpty()) {
            Node min = queue.delMin();
            for (int v : maze.adj(min.getSource())) {
                if (!marked[v]) {
                    marked[v] = true;
                    announce();
                    distTo[v] = distTo[min.getSource()] + 1;
                    edgeTo[v] = min.getSource();
                    announce();
                    queue.insert(new Node(v, h(v)));
                }
                if (v == t) {
                    targetFound = true;
                    return;
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

