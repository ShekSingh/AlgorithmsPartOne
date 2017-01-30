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

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Percolation {

    private int n;
    private int countOfOpenSites;
    private int[] sites;
    private int topVirtualIndex;
    private int bottomVirtualIndex;

    private WeightedQuickUnionUF uf;

    public Percolation(int n) {

        if (n <= 0)
            throw new java.lang.IllegalArgumentException();

        this.n = n;

        topVirtualIndex = 0;
        bottomVirtualIndex = (n * n) + 1;
        sites = new int[bottomVirtualIndex + 1];

        uf = new WeightedQuickUnionUF(bottomVirtualIndex + 1);

        // initialize all sites, including virtual sites
        for (int i = topVirtualIndex; i <= bottomVirtualIndex; i++) {

            sites[i] = i;

        }


    }

    public static void main(String[] args) {

        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        //  WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p, q);
            // if (uf.connected(p, q)) continue;
            // uf.union(p, q);
            //   StdOut.println(p + " " + q);
        }
        StdOut.println(perc.percolates());

    }

    public void open(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        if (!isOpen(row, col)) {

            // mark as open
            sites[index] = -1;
            countOfOpenSites++;

            // check for adjacent sites and connect if open


            // check right
            if ((col + 1) <= this.n) {

                if (isOpen(row, col + 1) && !uf.connected(xyTo1D(row, col), xyTo1D(row, col + 1))) {
                    // connect to open if not connected
                    uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                }

            }


            // check left
            if ((col - 1) >= 1) {

                if (isOpen(row, col - 1) && !uf.connected(xyTo1D(row, col), xyTo1D(row, col - 1))) {
                    // connect to open if not connected
                    uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                }

            }

            // check up
            if ((row - 1) >= 1) {

                if (isOpen(row - 1, col) && !uf.connected(xyTo1D(row, col), xyTo1D(row - 1, col))) {
                    // connect to open if not connected
                    uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                }

            }


            // check down
            if ((row + 1) <= this.n) {

                if (isOpen(row + 1, col) && !uf.connected(xyTo1D(row, col), xyTo1D(row + 1, col))) {
                    // connect to open if not connected
                    uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                }

            }


            // connect to virtual top
            if (row == 1 && !uf.connected(topVirtualIndex, xyTo1D(row, col))) {
                uf.union(topVirtualIndex, xyTo1D(row, col));
            }

            // connect to virtual bottom
            if (row == n && !uf.connected(bottomVirtualIndex, xyTo1D(row, col))) {
                uf.union(bottomVirtualIndex, xyTo1D(row, col));
            }


        }

    }

    public boolean isOpen(int row, int col) {

        validate(row, col);
        int index = xyTo1D(row, col);
        return (sites[index] == -1);

    }

    public boolean isFull(int row, int col) {

        validate(row, col);

        return uf.connected(xyTo1D(row, col), topVirtualIndex);

    }

    public int numberOfOpenSites() {
        return countOfOpenSites;
    }

    public boolean percolates() {

        return uf.connected(topVirtualIndex, bottomVirtualIndex);

    }

    private int xyTo1D(int row, int col) {
        return (this.n * (row - 1)) + col;
    }

    private void validate(int row, int col) {

        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new java.lang.IndexOutOfBoundsException();

    }

}
