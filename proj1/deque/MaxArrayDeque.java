package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> cp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T ans = get(headIndex + 1);
        for (int i = headIndex + 1; i <= headIndex + size(); i++) {
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
        T ans = get(headIndex + 1);
        for (int i = headIndex + 1; i <= headIndex + size(); i++) {
            T val = get(i % items.length);
            if (val != null && c.compare(ans, val) >= 0) {
                ans = val;
            }
        }
        return ans;
    }
}
