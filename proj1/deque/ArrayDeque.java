package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int headIndex;
    private int tailIndex;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        headIndex = 1;
        tailIndex = 1;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, headIndex, a, 0, size - headIndex);
        System.arraycopy(items, 0, a, size - headIndex, headIndex);
        items = a;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        headIndex--;
        if (headIndex < 0) {
            headIndex = items.length - 1;
        }
        if (headIndex == tailIndex) {
            tailIndex++;
        }
        items[headIndex] = x;
        size = size + 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        tailIndex++;
        items[tailIndex - 1] = x;
        size++;
    }

    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        return items[tailIndex - 1];
    }

    public T getfirst() {
        return items[headIndex];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public T get(int i) {
        return items[(headIndex + i) % size];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = getfirst();
        items[headIndex] = null;
        headIndex++;
        if (headIndex == items.length) {
            headIndex = 0;
        }
        size--;
        if (size < items.length / 4) {
            resize(items.length / 4);
            headIndex = 0;
            tailIndex = size;
        }
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = getLast();
        items[tailIndex - 1] = null;
        tailIndex--;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 4);
            headIndex = 0;
            tailIndex = size;
        }
        return x;
    }

    public void printDeque() {
        for (int i = headIndex; i < headIndex + size; i++) {
            System.out.print(items[i % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> testQueue = new ArrayDeque<>();
        testQueue.addFirst(2);
        testQueue.removeFirst();
        testQueue.addFirst(3);
        testQueue.addLast(5);
        testQueue.addLast(6);
        testQueue.addLast(7);
        testQueue.printDeque();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
