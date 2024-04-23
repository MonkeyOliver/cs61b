package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    protected T[] items;
    protected int size;
    protected int headIndex;
    protected int tailIndex;

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
    ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        headIndex = 7;
        tailIndex = 0;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        // TODO: robust
        T[] a = (T[]) new Object[capacity];
        if (tailIndex <= headIndex) {
            if (tailIndex >= 0) {
                System.arraycopy(items, 0, a, 0, tailIndex);
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
        items[headIndex] = x;
        headIndex--;
        size++;
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
        if (headIndex < 0) {
            headIndex = items.length - 1;
        }
    }

    @Override
    public void addLast(T x) {
        items[tailIndex] = x;
        tailIndex++;
        size++;
        if (headIndex == tailIndex) {
            resize(items.length * 2);
        }
        if (tailIndex == items.length) {
            tailIndex = 0;
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
    public void printDeque() {
        for (int i = headIndex + 1; i <= headIndex + size; i++) {
            System.out.print(items[i % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> testQueue = new ArrayDeque<>();
        for (int i = 0; i < 2; i++) {
            testQueue.addLast(i);
        }
        testQueue.addFirst(21);
        testQueue.addFirst(32);
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.addFirst(55);
        testQueue.addFirst(63);
        testQueue.addFirst(93);
        testQueue.addLast(7);
        testQueue.printDeque();
        System.out.println(testQueue.get(2));
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
