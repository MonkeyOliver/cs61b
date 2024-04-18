package deque;

import java.util.Comparator;

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
            if (cp.compare(ans, get(i)) > 0) {
                ans = items[i];
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
            if (c.compare(ans, get(i)) > 0) {
                ans = items[i];
            }
        }
        return ans;
    }

}
