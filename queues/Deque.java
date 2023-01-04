/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;

    private class Node {
        private Item item = null;
        private Node prev = null;
        private Node next = null;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque underflow");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        int s = 0;
        DequeIterator it = new DequeIterator();
        while (it.hasNext()) {
            s++;
            it.next();
        }
        return s;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else if (first == last) {
            first = new Node();
            first.prev = null;
            first.next = last;
            last.prev = first;
            last.next = null;
            first.item = item;
        }
        else {
            first.prev = new Node();
            first.prev.next = first;
            first.prev.prev = null;

            first = first.prev;
            first.item = item;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else if (first == last) {
            last = new Node();
            last.prev = first;
            last.next = null;
            first.prev = null;
            first.next = last;
            last.item = item;
        }
        else {
            last.next = new Node();
            last.next.prev = last;
            last.next.next = null;

            last = last.next;
            last.item = item;
        }
    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else if (first == last) {
            Item item = first.item;
            first = null;
            last = null;
            return item;
        }
        else {
            Item item = first.item;
            first = first.next;
            first.prev = null;
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else if (first == last) {
            Item item = last.item;
            first = null;
            last = null;
            return item;
        }
        else {
            Item item = last.item;
            last = last.prev;
            last.next = null;
            return item;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.println(d.isEmpty());
        System.out.println(d.size());
        d.addFirst(11);
        System.out.println(d.size());
        d.addFirst(12);
        d.addLast(13);
        System.out.println(d.size());
        System.out.println(d.removeLast());
        System.out.println(d.size());
        System.out.println(d.removeFirst());
        System.out.println(d.size());
        System.out.println(d.removeFirst());
        System.out.println(d.size());
    }
}
