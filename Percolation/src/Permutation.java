import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = StdIn.readInt();
        int i=0;
        String[] a = new String[k];
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            a[i++] = item;
        }

        StdRandom.shuffle(a);

        queue.enqueue(item);
    }
}

