package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int headIndex;
    private int tailIndex;

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[(headIndex + wizPos + 1) % items.length];
            wizPos += 1;
            return returnItem;
        }
    }


    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        headIndex = 7;
        tailIndex = 0;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (tailIndex <= headIndex) {
            if (tailIndex >= 0) {
                System.arraycopy(items, 0, a, 0, tailIndex + 1);
            }
            if (items.length - (headIndex + 1) >= 0) {
                System.arraycopy(items, headIndex + 1, a, headIndex + 1 + capacity - items.length, items.length - (headIndex + 1));
            }
            headIndex += capacity - items.length; // Update headIndex
        } else {
            if (size >= 0) {
                System.arraycopy(items, headIndex + 1, a, 0, size);
            }
            headIndex = a.length - 1;
            tailIndex = size;
        }
        items = a;
    }

    @Override
    public void addFirst(T x) {
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
        items[headIndex] = x;
        headIndex--;
        size++;
        if (headIndex < 0) {
            headIndex = items.length - 1;
        }
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
    }

    @Override
    public void addLast(T x) {
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
        items[tailIndex] = x;
        tailIndex++;
        size++;
        if (tailIndex == items.length) {
            tailIndex = 0;
        }
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
    }

    /**
     * Returns the item from the back of the list.
     */
    private T getLast() {
        if (tailIndex == 0) {
            return items[items.length - 1];
        }
        return items[tailIndex - 1];
    }

    private T getfirst() {
        return items[(headIndex + 1) % items.length];
    }

    public int getHeadIndex(){
        return headIndex + 1;
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    @Override
    public T get(int i) {
        return items[(headIndex + 1 + i) % items.length];
    }

    /**
     * Returns the number of items in the list.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = getfirst();
        headIndex++;
        if (headIndex == items.length) {
            headIndex = 0;
        }
        items[headIndex] = null;
        size--;
        if (size < items.length / 2) {
            resize(items.length / 2);
        }
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = getLast();
        tailIndex--;
        if (tailIndex < 0) {
            tailIndex = items.length - 1;
        }
        items[tailIndex] = null;
        size--;
        if (size < items.length / 2) {
            resize(items.length / 2);
        }
        return x;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        // Use instanceof like code below can be compiled in Java16 but can not be compiled on gradescope.
//        if (o instanceof ArrayDeque<?> other) {
//            if (this.size() != other.size()) {
//                return false;
//            }
//            int oidx = other.getHeadIndex();
//            for (int i = getHeadIndex(); i < getHeadIndex() + size; i++) {
//                if (this.get(i) != other.get(oidx)) {
//                    return false;
//                }
//                oidx++;
//            }
//            return true;
//        } else {
//            return false;
//        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        int oidx = other.headIndex + 1;
        for (int i = getHeadIndex(); i < getHeadIndex() + size; i++) {
            if (!this.items[i % items.length].equals(other.items[oidx % other.items.length])) {
                return false;
            }
            oidx++;
        }
        return true;
    }

    @Override
    public void printDeque() {
        for (int i = getHeadIndex(); i < getHeadIndex() + size; i++) {
            System.out.print(items[i % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }
}
