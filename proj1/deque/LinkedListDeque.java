package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        Node next, prev;
        T item;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private int cnt;
    private Node first;

    public LinkedListDeque(T x) {
        first = new Node(x, null, null);
        first.next = first;
        first.prev = first;
        cnt = 1;
    }

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
            return p.item;
        } else return null;
    }

    @Override
    public T removeLast() {
        if (cnt > 0) {
            Node p = first.prev;
            first.prev.prev.next = first;
            first.prev = first.prev.prev;
            cnt--;
            return p.item;
        } else return null;
    }

    @Override
    public T get(int index) {
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
        return getRecursiveHelper(first, index);
    }

    @Override
    public int size() {
        return cnt;
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

    public static void main(String[] args) {
        LinkedListDeque<Integer> testQueue = new LinkedListDeque<>(1);
        testQueue.addFirst(2);
        testQueue.addFirst(3);
        testQueue.addLast(5);
        testQueue.addLast(6);
        System.out.println(testQueue.getRecursive(0));
        System.out.println(testQueue.get(0));
        testQueue.printDeque();
        testQueue.removeFirst();
        testQueue.removeLast();
        testQueue.printDeque();
        System.out.println(testQueue.size());
    }
}
