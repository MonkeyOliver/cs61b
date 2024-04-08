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
        items = (T[]) new Object[100];
        size = 0;
        headIndex = 0;
        tailIndex = 0;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        headIndex--;
        if (headIndex < 0) {
            headIndex = size - headIndex;
        }
        items[headIndex] = x;
        size = size + 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tailIndex] = x;
        size++;
        tailIndex++;
    }

    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        return items[tailIndex];
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

    public T removeFirst() {
        T x = getfirst();
        items[headIndex] = null;
        headIndex++;
        size--;
        return x;
    }

    public T removeLast() {
        T x = getLast();
        items[tailIndex] = null;
        tailIndex--;
        size--;
        return x;
    }

    public void printDeque() {
        for (int i = headIndex; i < tailIndex; i++) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> testQueue = new ArrayDeque<>();
//        testQueue.addFirst(2);
//        testQueue.addFirst(3);
        testQueue.addLast(5);
        testQueue.addLast(6);
        testQueue.addLast(7);
        testQueue.printDeque();
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
