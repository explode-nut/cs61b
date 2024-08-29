package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF set;
    private int size;
    private boolean isPercolated;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        set = new WeightedQuickUnionUF(N * N);
        size = 0;
        isPercolated = false;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col) || isFull(row, col)) {
            return;
        }
        if (row != 0) {
            grid[row][col] = 1;
        } else {
            grid[row][col] = 2;
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
            }
        }
        if (col != 0) {
            if (isOpen(row, col - 1)) {
                set.union(xyToInt(row, col), xyToInt(row, col - 1));
            }
        }
        if (col != grid[0].length - 1) {
            if (isOpen(row, col + 1)) {
                set.union(xyToInt(row, col), xyToInt(row, col + 1));
            }
        }
        if (row != grid.length - 1) {
            if (isOpen(row + 1, col)) {
                set.union(xyToInt(row, col), xyToInt(row + 1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IllegalArgumentException();
        }
        return grid[row][col] == 1 || grid[row][col] == 2;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row > grid.length || col > grid[0].length) {
            throw new IllegalArgumentException();
        }
        return grid[row][col] == 2 || isConnectedToFull(row, col);
    }

    private boolean isConnectedToFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        for (int i = 0; i < grid[0].length; i++) {
            // 必须加上isOpen(0, i)，否则在初始化时会使第一行的每一个元素对自己查询是否连接，进而使第一行直接被初始化为full状态
            if (set.connected(xyToInt(row, col), xyToInt(0, i)) && isOpen(0, i)) {
                return true;
            }
        }
        return false;
    }
    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }
    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < grid[0].length; i++) {
            if (isPercolated) {
                break;
            }
            if (isConnectedToFull(grid.length - 1, i)) {
                isPercolated = true;
            }
        }
        return isPercolated;
    }
}
