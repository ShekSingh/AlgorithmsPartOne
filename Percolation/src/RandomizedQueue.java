import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>  {

    private Item[] a;
    private int n;
    private int head;
    private int tail;



    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
        head = 0;
        tail = 0;
    }




    public void enqueue(Item item){

        if (n == a.length) resize(n*2);
        a[tail] = item;
        tail++;

    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[head];
        a[head] = null;
        head ++;
        n--;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample() {
      return a[StdRandom.uniform(1, n + 1)];
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity) {

        int oldLength = a.length;
       Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
                temp[i] = a[i + head];
                tail++;

        }
        a = temp;


        if(capacity > oldLength)
        tail = n;
        else
         tail= (head % capacity) - 1;

        head=0;

    }
  

    public Iterator<Item> iterator() {
        return null;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;

        public RandomizedQueueIterator() {
            i = n-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }


    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if(item.equals("*")) {
                StdOut.print(queue.sample() + " ");
            }

            else {

                if (!item.equals("-")) queue.enqueue(item);
                else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");

            }
        }
        StdOut.println("(" + queue.size() + " left on stack)");
    }

}


