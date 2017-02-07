import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }


    public boolean isEmpty() {

        return (first == null && last == null);
    }

    public int size() {

        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if (oldFirst != null)
            oldFirst.prev = first;

        // its its the first node then its also the last node
        if (size() == 0)
            last = first;

        n++;

    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;

        // its its the first node then its also the last node
        if (oldLast != null)
            oldLast.next = last;
        if (size() == 0)
            first = last;

        n++;

    }

    public Item removeLast() {

        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;

        if (last != null)
            last.next = null;

        n--;
        if (size() == 0)
            first = last;
        return item;

    }

    public Item removeFirst() {

        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;

        if (first != null)
            first.prev = null;

        n--;

        if (size() == 0)
            last = first;
        return item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Deque<String> stack = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (item.equals("-"))
                StdOut.print(stack.removeFirst() + " ");
            else if (item.equals("+"))
                StdOut.print(stack.removeLast() + " ");

            else if (item.equals("*")) {
                Iterator<String> iterator = stack.iterator();
                while (iterator.hasNext()) {

                    StdOut.print(iterator.next());
                }

            } else if (Integer.parseInt(item) >= 0)
                stack.addFirst(item);


            else
                stack.addLast(item);

        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

}
