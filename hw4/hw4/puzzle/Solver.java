package hw4.puzzle;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
    private int size;
    private MinPQ<WorldState> minPQ;
    private ArrayList<WorldState> path;
    private HashSet<WorldState> parents;

    public Solver(WorldState initial) {
        minPQ = new MinPQ<>((o1, o2) -> {
            int i = o1.estimatedDistanceToGoal();
            int j = o2.estimatedDistanceToGoal();
            if (i > j) {
                return 1;
            } else if (i < j) {
                return -1;
            } else {
                return 0;
            }
        });
        path = new ArrayList<>();
        parents = new HashSet<>();
        size = 0;

        operateMinPQ(initial);
    }

    private void operateMinPQ(WorldState min) {
        /*if (min.isGoal()) {
            return;
        }*/
        parents.add(min);
        Iterable<WorldState> neighbors = min.neighbors();
        for (WorldState w : neighbors) {
            if (!parents.contains(w)) {
                minPQ.insert(w);
            }
        }
        WorldState nextMin = minPQ.delMin();
        if (nextMin.isGoal()) {
            return;
        }
        size += 1;
        path.add(nextMin);
        operateMinPQ(nextMin);
    }

    public int moves() {
        return size;
    }
    public Iterable<WorldState> solution() {
        return path;
    }
}
