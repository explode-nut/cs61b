package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private final int N;
    private final int T;
    private final double[] xi;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.xi = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation grid = pf.make(N);
            int[] permutation = StdRandom.permutation(N * N);
            int index = 0;
            while (!grid.percolates()) {
                int[] rowAndCol = randomToXY(permutation[index]);
                grid.open(rowAndCol[0], rowAndCol[1]);
                index++;
            }
            xi[i] = (double) grid.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * 将随机位置转换为row，col
     * @param random random为随机生成的grid中的位置
     * @return result result中索引0为row，1为col
     */

    private int[] randomToXY(int random) {
        int[] result = new int[2];
        result[0] = random / N;
        result[1] = random % N;
        return result;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xi);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xi);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
    }
}
