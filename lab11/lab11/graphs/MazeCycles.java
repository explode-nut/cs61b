package lab11.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private boolean hasCycle;
    private Stack<Integer> stack;
    private int start; // 环的起点
    private int end; // 环的终点

    public MazeCycles(Maze m) {
        super(m);
        this.maze = m;
        this.s = maze.xyTo1D(1, 1);
        this.t = maze.xyTo1D(m.N(), m.N());
        this.start = 0;
        this.end = 0;
        distTo[s] = 0;
        this.hasCycle = false;
        this.stack = new Stack<>();
    }

    @Override
    public void solve() {
        dfs(s);
        if (hasCycle) {
            while (stack.peek() != start) {
                Integer pop = stack.pop();
                edgeTo[stack.peek()] = pop;
                announce();
            }
            edgeTo[end] = start;
            announce();
        }
    }

    // Helper methods go here
    private void dfs(int v) {
        if (hasCycle) {
            return;
        }

        marked[v] = true;
        stack.push(v);
        announce();

        for (int w : maze.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (!marked[w]) {
                distTo[w] = distTo[v] + 1;
                dfs(w);
            } else {
                if (distTo[w] != distTo[v] - 1) {
                    hasCycle = true;
                    start = w;
                    end = v;
                }
                if (hasCycle) {
                    return;
                }
            }
        }

    }
}

