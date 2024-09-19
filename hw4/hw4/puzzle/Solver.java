package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solver {
    MinPQ<Node> set = new MinPQ<>();

    private class Node implements Comparable<Node> {
        private final WorldState initial;
        private final int moves;
        private final Node previous;
        private int weight;

        public Node(WorldState initial, int moves, Node previous) {
            this.initial = initial;
            this.moves = moves;
            this.previous = previous;
            this.weight = moves + initial.estimatedDistanceToGoal();
        }

        public WorldState getInitial() {
            return initial;
        }

        public int getWeight() {
            return weight;
        }

        public int getMoves() {
            return moves;
        }

        public Node getPrevious() {
            return previous;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public Solver(WorldState initial) {
        set.insert(new Node(initial, 0, null));
        solve();
    }

    public int moves() {
        return set.min().getMoves();
    }

    public Iterable<WorldState> solution() {
        List<WorldState> result = new ArrayList<>();
        Stack<WorldState>stack=new Stack<>();
        Node pos = set.min();
        while (pos!=null) {
            stack.push(pos.getInitial());
            pos=pos.previous;
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return Collections.unmodifiableCollection(result);
    }

    private void solve() {
        while (!set.min().getInitial().isGoal()) {
            Node min = set.delMin();
            Iterable<WorldState> neighbors = min.getInitial().neighbors();
            for (WorldState i : neighbors) {
                if (min.getPrevious() == null || !i.equals(min.getPrevious().getInitial())) {
                    set.insert(new Node(i, min.getMoves() + 1, min));
                }
            }
        }
    }
}
