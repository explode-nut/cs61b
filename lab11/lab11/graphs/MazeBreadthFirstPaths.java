package lab11.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue = new LinkedList<>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        queue.offer(s);
        marked[s] = true;
        announce();
        if (s == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }

        while (!queue.isEmpty()) {
            Integer v = (Integer) queue.poll();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    queue.offer(w);
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

