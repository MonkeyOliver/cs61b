package deque;

import java.util.Comparator;

class intComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> cp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T ans = items[headIndex + 1];
        for (int i = headIndex + 1; i <= headIndex + size; i++) {
            T val = get(i % items.length);
            if (val != null && cp.compare(ans, val) >= 0) {
                ans = val;
            }
        }
        return ans;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T ans = items[headIndex + 1];
        for (int i = headIndex + 1; i <= headIndex + size; i++) {
            T val = get(i % items.length);
            if (val != null && c.compare(ans, val) >= 0) {
                ans = val;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxArrayDeque<Integer> testQueue = new MaxArrayDeque<>(new intComparator());
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
        System.out.println(testQueue.max());
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
