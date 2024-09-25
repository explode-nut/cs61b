package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private int[][] grid;
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF setWithoutBackWash;
    private int size;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        set = new WeightedQuickUnionUF(N * N + 5);
        setWithoutBackWash = new WeightedQuickUnionUF(N * N + 5);
        size = 0;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col) || isFull(row, col)) {
            return;
        }
        if (row != 0) {
            grid[row][col] = 1;
        } else {
            grid[row][col] = 1;
            setWithoutBackWash.union(xyToInt(row, col), N * N + 1);
            set.union(xyToInt(row, col), N * N + 1);
        }
        if (row == grid.length - 1) {
            set.union(xyToInt(row, col), N * N + 2);
        }
        size += 1;
        // scan and union
        scanAndUnion(row, col);
    }

    private int xyToInt(int row, int col) {
        return col + row * grid[0].length;
    }

    private void scanAndUnion(int row, int col) {
        if (row != 0) {
            if (isOpen(row - 1, col)) {
                set.union(xyToInt(row, col), xyToInt(row - 1, col));
                setWithoutBackWash.union(xyToInt(row, col), xyToInt(row - 1, col));
            }
        }
        if (col != 0) {
            if (isOpen(row, col - 1)) {
                set.union(xyToInt(row, col), xyToInt(row, col - 1));
                setWithoutBackWash.union(xyToInt(row, col), xyToInt(row, col - 1));
            }
        }
        if (col != grid[0].length - 1) {
            if (isOpen(row, col + 1)) {
                set.union(xyToInt(row, col), xyToInt(row, col + 1));
                setWithoutBackWash.union(xyToInt(row, col), xyToInt(row, col + 1));
            }
        }
        if (row != grid.length - 1) {
            if (isOpen(row + 1, col)) {
                set.union(xyToInt(row, col), xyToInt(row + 1, col));
                setWithoutBackWash.union(xyToInt(row, col), xyToInt(row + 1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IndexOutOfBoundsException();
        }
        return setWithoutBackWash.connected(xyToInt(row, col), N * N + 1);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }
    // does the system percolate?
    public boolean percolates() {
        return set.connected(N * N + 1, N * N + 2);
    }
}
