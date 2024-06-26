package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        Node next, prev;
        T item;

        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos;
        private int index;

        LinkedListDequeIterator() {
            wizPos = first;
            index = cnt;
        }

        public boolean hasNext() {
            return index > 0;
        }

        public T next() {
            T val = wizPos.item;
            wizPos = wizPos.next;
            index--;
            return val;
        }
    }

    private int cnt;
    private Node first;

    public LinkedListDeque() {
        first = null;
        cnt = 0;
    }

    @Override
    public void addFirst(T item) {
        if (first == null) {
            first = new Node(item, null, null);
            first.next = first;
            first.prev = first;
            cnt = 1;
        } else {
            Node p = first.prev;
            first = new Node(item, first, first.prev);
            p.next = first;
            first.next.prev = first;
            cnt++;
        }
    }

    @Override
    public void addLast(T item) {
        if (first == null) {
            first = new Node(item, null, null);
            first.next = first;
            first.prev = first;
            cnt = 1;
        } else {
            Node p = new Node(item, first, first.prev);
            first.prev.next = p;
            first.prev = p;
            cnt++;
        }
    }

    @Override
    public T removeFirst() {
        if (cnt > 0) {
            Node p = first;
            first.prev.next = first.next;
            first.next.prev = first.prev;
            first = first.next;
            cnt--;
            if (cnt == 0) {
                first = null;
            }
            return p.item;
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (cnt > 0) {
            Node p = first.prev;
            first.prev.prev.next = first;
            first.prev = first.prev.prev;
            cnt--;
            if (cnt == 0) {
                first = null;
            }
            return p.item;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        if (cnt == 0) {
            return null;
        }
        Node p = first;
        while (index >= 0) {
            index--;
            if (index < 0) {
                return p.item;
            } else {
                p = p.next;
            }
        }
        return null;
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (cnt > 0) {
            return getRecursiveHelper(first, index);
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return cnt;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        // Use instanceof like code below can be
        // compiled in Java16 but can not be
        // compiled on gradescope.
//        if (o instanceof Deque<?> other) {
//            if (this.size() != other.size()) {
//                return false;
//            }
//            for (int i = 0; i < size(); i++) {
//                if (!this.get(i).equals(other.get(i))) {
//                    return false;
//                }
//            }
//            return true;
//        } else {
//            return false;
//        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void printDeque() {
        Node p = first;
        int index = cnt;
        while (index > 0) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
            index--;
        }
        System.out.println();
    }
}
