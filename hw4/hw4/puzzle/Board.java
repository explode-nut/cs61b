package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {
    private final int[][] tiles;
    private final int[][] goal;
    private final static int BLANK = 0;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        this.goal = new int[tiles.length][tiles.length];
        // 复制数组
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
        // 初始化目标数组
        int tmp = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                goal[i][j] = tmp;
                tmp++;
            }
        }
        goal[tiles.length - 1][tiles.length - 1] = BLANK;
    }
    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= this.tiles.length || j >= this.tiles.length) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size() {
        return this.tiles.length;
    }
    /** Returns the neighbors of the current board. */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int result = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tileAt(i, j) != BLANK && this.tileAt(i, j) != goal[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tileAt(i, j) != BLANK && this.tileAt(i, j) != goal[i][j]) {
                    int number = this.tiles[i][j];
                    int row = (number - 1) / this.tiles.length;
                    int col = (number - 1) - row * this.tiles.length;
                    result += (Math.abs(row - i) + Math.abs(col - j));
                }
            }
        }
        return result;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board a = (Board) y;
        if (this.size() != a.size()) {
            return false;
        }
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tileAt(i, j) != a.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
