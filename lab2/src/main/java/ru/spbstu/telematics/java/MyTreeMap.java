package main.java.ru.spbstu.telematics.java;
import java.util.*;
/**
 * Created by ED on 19.12.2017.
 */
public class MyTreeMap<K, V>extends AbstractMap<K, V> implements NavigableMap<K, V>{
    static int RED = -1,
            BLACK = 1;

    public MyTreeMap()
    {
        fabricateTree(0);
    }

    public K firstKey() {
        if (root == nil)
            throw new NoSuchElementException();
        return firstNode().key;
    }



    final MyTreeMap.Node<K, V> lowestGreaterThan(K key, boolean first, boolean equal) {
        if (key == nil)
            return first ? firstNode() : nil;

        MyTreeMap.Node<K, V> last = nil;
        MyTreeMap.Node<K, V> current = root;
        int comparison = 0;

        while (current != nil) {
            last = current;
            comparison = compare(key, current.key);
            if (comparison > 0)
                current = current.right;
            else if (comparison < 0)
                current = current.left;
            else
                return (equal ? current : successor(current));
        }
        return comparison > 0 ? successor(last) : last;
    }



    static class Node<K, V> {


        int color;

        K key;
        V value;


        MyTreeMap.Node<K, V> left = nil;

        MyTreeMap.Node<K, V> right = nil;

        MyTreeMap.Node<K, V> parent = nil;

        V setValue(V value)
        {
            return this.value=value;
        }

        Node(K key, V value, int color) {
            this.key=key;
            this.value=value;
            this.color = color;
        }
    }
    private transient MyTreeMap.Node root;
    transient int size;
    private transient Set<Map.Entry<K, V>> entries;
    private transient NavigableMap<K, V> descendingMap;
    private transient NavigableSet<K> nKeys;
    transient int modCount;
    static final MyTreeMap.Node nil = new MyTreeMap.Node(null, null, BLACK);

    static {

        nil.parent = nil;
        nil.left = nil;
        nil.right = nil;
    }
    private void fabricateTree(final int count) {
        if (count == 0) {
            root = nil;
            size = 0;
            return;
        }
        root = new MyTreeMap.Node(null, null, BLACK);
        size = count;
        MyTreeMap.Node row = root;
        int rowsize;


        for (rowsize = 2; rowsize + rowsize <= count; rowsize <<= 1) {
            MyTreeMap.Node parent = row;
            MyTreeMap.Node last = null;
            for (int i = 0; i < rowsize; i += 2) {
                MyTreeMap.Node left = new MyTreeMap.Node(null, null, BLACK);
                MyTreeMap.Node right = new MyTreeMap.Node(null, null, BLACK);
                left.parent = parent;
                left.right = right;
                right.parent = parent;
                parent.left = left;
                MyTreeMap.Node next = parent.right;
                parent.right = right;
                parent = next;
                if (last != null)
                    last.right = left;
                last = right;
            }
            row = row.left;
        }


        int overflow = count - rowsize;
        MyTreeMap.Node parent = row;
        int i;
        for (i = 0; i < overflow; i += 2) {
            MyTreeMap.Node left = new MyTreeMap.Node(null, null, RED);
            MyTreeMap.Node right = new MyTreeMap.Node(null, null, RED);
            left.parent = parent;
            right.parent = parent;
            parent.left = left;
            MyTreeMap.Node next = parent.right;
            parent.right = right;
            parent = next;
        }

        if (i - overflow == 0) {
            MyTreeMap.Node left = new MyTreeMap.Node(null, null, RED);
            left.parent = parent;
            parent.left = left;
            parent = parent.right;
            left.parent.right = nil;
        }

        while (parent != nil) {
            MyTreeMap.Node next = parent.right;
            parent.right = nil;
            parent = next;
        }
    }
    final int compare(K o1, K o2) {
        return ((Comparable) o1).compareTo(o2);
    }
    private void rotateLeft(MyTreeMap.Node<K, V> node) {
        MyTreeMap.Node child = node.right;




        node.right = child.left;
        if (child.left != nil)
            child.left.parent = node;


        child.parent = node.parent;
        if (node.parent != nil) {
            if (node == node.parent.left)
                node.parent.left = child;
            else
                node.parent.right = child;
        } else
            root = child;


        child.left = node;
        node.parent = child;
    }
    private void rotateRight(MyTreeMap.Node<K, V> node) {
        MyTreeMap.Node child = node.left;




        node.left = child.right;
        if (child.right != nil)
            child.right.parent = node;


        child.parent = node.parent;
        if (node.parent != nil) {
            if (node == node.parent.right)
                node.parent.right = child;
            else
                node.parent.left = child;
        } else
            root = child;


        child.right = node;
        node.parent = child;
    }
    private void insertFixup(MyTreeMap.Node<K, V> n) {



        while (n.parent.color == RED && n.parent.parent != nil) {
            if (n.parent == n.parent.parent.left) {
                MyTreeMap.Node uncle = n.parent.parent.right;

                if (uncle.color == RED) {


                    n.parent.color = BLACK;
                    uncle.color = BLACK;
                    uncle.parent.color = RED;
                    n = uncle.parent;
                } else {
                    if (n == n.parent.right) {


                        n = n.parent;
                        rotateLeft(n);
                    }


                    n.parent.color = BLACK;
                    n.parent.parent.color = RED;
                    rotateRight(n.parent.parent);
                }
            } else {

                MyTreeMap.Node uncle = n.parent.parent.left;

                if (uncle.color == RED) {


                    n.parent.color = BLACK;
                    uncle.color = BLACK;
                    uncle.parent.color = RED;
                    n = uncle.parent;
                } else {
                    if (n == n.parent.left) {


                        n = n.parent;
                        rotateRight(n);
                    }


                    n.parent.color = BLACK;
                    n.parent.parent.color = RED;
                    rotateLeft(n.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }
    final MyTreeMap.Node<K, V> firstNode() {

        MyTreeMap.Node node = root;
        while (node.left != nil)
            node = node.left;
        return node;
    }
    final MyTreeMap.Node<K, V> successor(MyTreeMap.Node<K, V> node) {
        if (node.right != nil) {
            node = node.right;
            while (node.left != nil)
                node = node.left;
            return node;
        }

        MyTreeMap.Node<K, V> parent = node.parent;

        while (node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
    final void removeNode(MyTreeMap.Node<K, V> node) {
        MyTreeMap.Node<K, V> splice;
        MyTreeMap.Node<K, V> child;

        modCount++;
        size--;


        if (node.left == nil) {

            splice = node;
            child = node.right;
        } else if (node.right == nil) {

            splice = node;
            child = node.left;
        } else {


            splice = node.left;
            while (splice.right != nil)
                splice = splice.right;
            child = splice.left;
            node.key = splice.key;
            node.value = splice.value;
        }


        MyTreeMap.Node parent = splice.parent;
        if (child != nil)
            child.parent = parent;
        if (parent == nil) {

            root = child;
            return;
        }
        if (splice == parent.left)
            parent.left = child;
        else
            parent.right = child;

        if (splice.color == BLACK)
            deleteFixup(child, parent);
    }
    private void deleteFixup(MyTreeMap.Node<K, V> node, MyTreeMap.Node<K, V> parent) {





        while (node != root && node.color == BLACK) {
            if (node == parent.left) {

                MyTreeMap.Node<K, V> sibling = parent.right;


                if (sibling.color == RED) {


                    sibling.color = BLACK;
                    parent.color = RED;
                    rotateLeft(parent);
                    sibling = parent.right;
                }

                if (sibling.left.color == BLACK && sibling.right.color == BLACK) {


                    sibling.color = RED;
                    node = parent;
                    parent = parent.parent;
                } else {
                    if (sibling.right.color == BLACK) {


                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = parent.right;
                    }


                    sibling.color = parent.color;
                    parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(parent);
                    node = root;
                }
            } else {

                MyTreeMap.Node<K, V> sibling = parent.left;


                if (sibling.color == RED) {


                    sibling.color = BLACK;
                    parent.color = RED;
                    rotateRight(parent);
                    sibling = parent.left;
                }

                if (sibling.right.color == BLACK && sibling.left.color == BLACK) {


                    sibling.color = RED;
                    node = parent;
                    parent = parent.parent;
                } else {
                    if (sibling.left.color == BLACK) {


                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }


                    sibling.color = parent.color;
                    parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    public int size() {
        return size;
    }
    public boolean containsKey(Object key) {
        return getNode((K) key) != nil;
    }
    public V get(Object key) {

        return getNode((K) key).value;
    }


    public boolean containsValue(Object value) {
        MyTreeMap.Node node = firstNode();
        while (node != nil) {
            if (value.equals(node.value))
                return true;
            node = successor(node);
        }
        return false;
    }
    public V put(K key, V value) {
        MyTreeMap.Node<K, V> current = root;
        MyTreeMap.Node<K, V> parent = nil;
        int comparison = 0;


        while (current != nil) {
            parent = current;
            comparison = compare(key, current.key);
            if (comparison > 0)
                current = current.right;
            else if (comparison < 0)
                current = current.left;
            else
                return current.setValue(value);
        }


        MyTreeMap.Node n = new MyTreeMap.Node(key, value, RED);
        n.parent = parent;


        modCount++;
        size++;
        if (parent == nil) {

            root = n;
            return null;
        }
        if (comparison > 0)
            parent.right = n;
        else
            parent.left = n;


        insertFixup(n);
        return null;
    }
    public V remove(Object key) {
        MyTreeMap.Node<K, V> n = getNode((K) key);
        if (n == nil)
            return null;

        V result = n.value;
        removeNode(n);
        return result;
    }
    @Override
    public K lastKey() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    final MyTreeMap.Node<K, V> getNode(K key) {
        MyTreeMap.Node<K, V> current = root;
        while (current != nil) {
            int comparison = compare(key, current.key);
            if (comparison > 0)
                current = current.right;
            else if (comparison < 0)
                current = current.left;
            else
                return current;
        }
        return current;
    }

    @Override
    public Entry<K, V> lowerEntry(K k) {
        return null;
    }

    @Override
    public K lowerKey(K k) {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K k) {
        return null;
    }

    @Override
    public K floorKey(K k) {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K k) {
        return null;
    }

    @Override
    public K ceilingKey(K k) {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K k) {
        return null;
    }

    @Override
    public K higherKey(K k) {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<K, V> subMap(K k, boolean b, K k1, boolean b1) {
        return null;
    }

    @Override
    public NavigableMap<K, V> headMap(K k, boolean b) {
        return null;
    }

    @Override
    public NavigableMap<K, V> tailMap(K k, boolean b) {
        return null;
    }

    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    @Override
    public SortedMap<K, V> subMap(K k, K k1) {
        return null;
    }

    @Override
    public SortedMap<K, V> headMap(K k) {
        return null;
    }

    @Override
    public SortedMap<K, V> tailMap(K k) {
        return null;
    }
}
