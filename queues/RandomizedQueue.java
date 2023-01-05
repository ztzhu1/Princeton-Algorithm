/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node sentinel = null;

    private class Node {
        private Item item = null;
        private Node next = null;
    }

    private class QueueIterator implements Iterator<Item> {
        private Node first = sentinel.next;
        private Item[] items;
        private boolean[] accessed;

        public QueueIterator() {
            Node n = first;
            int s = 0;
            for (; n != null; n = n.next) {
                s++;
            }
            items = (Item[]) new Object[s];
            accessed = new boolean[s];
            n = first;
            for (int i = 0; i < items.length; i++) {
                items[i] = n.item;
                n = n.next;
                accessed[i] = false;
            }
        }

        public boolean hasNext() {
            for (int i = 0; i < accessed.length; i++) {
                if (!accessed[i]) {
                    return true;
                }
            }
            return false;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue underflow");
            }
            int s = items.length;
            int index = StdRandom.uniformInt(s);
            while (accessed[index]) {
                index = StdRandom.uniformInt(s);
            }
            accessed[index] = true;
            Node n = first;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        sentinel = new Node();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        int s = 0;
        QueueIterator it = new QueueIterator();
        while (it.hasNext()) {
            s++;
            it.next();
        }
        return s;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node n = sentinel;
        while (n.next != null) {
            n = n.next;
        }
        n.next = new Node();
        n.next.item = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size());
        Node n = sentinel;
        for (int i = 0; i < index; ++i) {
            n = n.next;
        }
        Item item = n.next.item;
        n.next = n.next.next;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size());
        Node n = sentinel;
        for (int i = 0; i < index; ++i) {
            n = n.next;
        }
        Item item = n.next.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        System.out.println(r.size());
        r.enqueue(11);
        r.enqueue(12);
        r.enqueue(13);
        RandomizedQueue.QueueIterator a = (RandomizedQueue.QueueIterator) r.iterator();
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.next());
        System.out.println(a.hasNext());
    }
}
