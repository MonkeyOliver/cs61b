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
        headIndex = 7;
        tailIndex = 0;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        //TODO: edge cases
        T[] a = (T[]) new Object[capacity];
        if (tailIndex >= 0) System.arraycopy(items, 0, a, 0, tailIndex);
        if (items.length - (headIndex + 1) >= 0)
            System.arraycopy(items, headIndex + 1, a, headIndex + 1 + capacity - items.length, items.length - (headIndex + 1));
        items = a;
    }

    public void addFirst(T x) {
        items[headIndex] = x;
        headIndex--;
        size++;
        if (headIndex == tailIndex) {
            int capacity = items.length;
            resize(capacity * 2);
            headIndex += items.length - capacity;
        }
    }

    public void addLast(T x) {
        items[tailIndex] = x;
        tailIndex++;
        size++;
        if (headIndex == tailIndex) {
            int capacity = items.length;
            resize(capacity * 2);
            headIndex += items.length - capacity;
        }
    }

    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        if (tailIndex == 0) {
            return items[items.length - 1];
        }
        return items[tailIndex - 1];
    }

    public T getfirst() {
        return items[(headIndex + 1) % items.length];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public T get(int i) {
        return items[(headIndex + 1 + i) % items.length];
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
        headIndex++;
        if (headIndex == items.length) {
            headIndex = 0;
        }
        items[headIndex] = null;
        size--;
//        if (size < items.length / 4) {
//            resize(items.length / 4);
//        }
        return x;
    }

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
//        if (size < items.length / 4) {
//            resize(items.length / 4);
//        }
        return x;
    }

    public void printDeque() {
        for (int i = headIndex + 1; i <= headIndex + size; i++) {
            System.out.print(items[i % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> testQueue = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            testQueue.addLast(i);
        }
        testQueue.addFirst(21);
        testQueue.addFirst(32);
        testQueue.addFirst(55);
        testQueue.addFirst(63);
        testQueue.addLast(7);
        testQueue.printDeque();
        System.out.println(testQueue.get(2));
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
