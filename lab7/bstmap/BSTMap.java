package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size;

    private class BSTNode {
        K key;
        V value;
        BSTNode left, right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public BSTNode find(BSTNode node, K key) {
            if (node == null) {
                return null;
            }
            if (key.equals(node.key)) {
                return node;
            } else if (key.compareTo(node.key) < 0) {
                return find(node.left, key);
            } else {
                return find(node.right, key);
            }
        }

        public BSTNode insert(BSTNode node, K key, V value) {
            if (node == null) {
                size++;
                return new BSTNode(key, value);
            }
            if (key.compareTo(node.key) < 0) {
                node.left = insert(node.left, key, value);
            } else {
                node.right = insert(node.right, key, value);
            }
            return node;
        }
    }

    private BSTNode root = null;

    public BSTMap() {
        this.size = 0;
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        BSTNode node = root.find(root, key);
        return node != null;
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return root.find(root, key).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (size == 0) {
            root = new BSTNode(key, value);
            size++;
        } else root.insert(root, key, value);
    }

    @Override
    public Set<K> keySet() throws UnsupportedOperationException {
        return Set.of();
    }

    @Override
    public V remove(K key) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public V remove(K key, V value) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Iterator<K> iterator() throws UnsupportedOperationException {
        return null;
    }
}
