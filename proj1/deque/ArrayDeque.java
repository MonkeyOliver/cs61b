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
        //TODO
        T[] a = (T[]) new Object[capacity];
//        System.arraycopy(items, headIndex, a, capacity - size + headIndex, size - headIndex);
//        System.arraycopy(items, 0, a, 0, tailIndex);
//        items = a;
//        headIndex = capacity - size + headIndex;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[headIndex] = x;
        headIndex--;
        if (headIndex < 0) {
            headIndex = items.length - 1;
        }
        size++;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tailIndex] = x;
        tailIndex++;
        size++;
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
        items[headIndex] = null;
        if (headIndex == items.length) {
            //TODO
            headIndex = 0;
        }
        size--;
        if (size < items.length / 4) {
            resize(items.length / 4);
        }
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = getLast();
        tailIndex--;
        if (tailIndex < 0) {
            //TODO
            tailIndex = items.length - 1;
        }
        items[tailIndex] = null;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 4);
        }
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
        testQueue.addFirst(2);
        testQueue.addFirst(3);
        testQueue.addLast(5);
        testQueue.addLast(6);
        testQueue.addLast(7);
        testQueue.printDeque();
        System.out.println(testQueue.get(2));
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
