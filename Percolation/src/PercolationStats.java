/******************************************************************************
 *  Name:    Abhishek Singh
 *  NetID:   N/A
 *  Precept: N/A
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Modeling Percolation using an N-by-N grid and Union-Find data
 *                structures to determine the threshold.
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private int n;
    private int trials;
    private Percolation perc;


    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        this.n = n;
        this.trials = trials;


    }

    public double mean() {

        int row;
        int col;

        double[] thresh = new double[this.trials];
        // perform experiment t times
        for (int i = 0; i < trials; i++) {

            this.perc = new Percolation(n);

            while (!this.perc.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                if (!this.perc.isOpen(row, col))
                    this.perc.open(row, col);

            }

            thresh[i] = (double) this.perc.numberOfOpenSites() / (n * n);


        }

        return StdStats.mean(thresh);

    }

    public double stddev() {

        if (this.trials == 1)
            return Double.NaN;

        int row;
        int col;

        double[] thresh = new double[this.trials];
        // perform experiment t times
        for (int i = 0; i < trials; i++) {

            this.perc = new Percolation(n);

            while (!this.perc.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                if (!this.perc.isOpen(row, col))
                    this.perc.open(row, col);

            }

            thresh[i] = (double) this.perc.numberOfOpenSites() / (n * n);


        }

        return StdStats.stddev(thresh, 1, this.trials - 1);


    }

    public double confidenceLo() {

        return (mean() - ((1.96 * stddev()) / (Math.sqrt(this.trials))));

    }

    public double confidenceHi() {

        return (mean() + ((1.96 * stddev()) / (Math.sqrt(this.trials))));

    }


    public static void main(String[] args) {

        int n = StdIn.readInt();
        int t = StdIn.readInt();

        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval  = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + " ]");

    }

}
