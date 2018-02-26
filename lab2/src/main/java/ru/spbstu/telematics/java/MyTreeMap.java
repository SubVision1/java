package main.java.ru.spbstu.telematics.java;
import java.util.*;
/**
 * Created by ED on 19.12.2017.
 */
public class MyTreeMap<K, V>implements NavigableMap<K, V>{

    class Node<K,V>
    {
     K key;
     V value;
     Node left;
     Node right;
     Node parent;
     Node(K key, V value,Node parent)
     {
         this.key=key;
         this.value=value;
         this.parent=parent;
     }
    }
    Node root;
    int size=0;
    Set<K>keySet=new HashSet<K>();


    @Override
    public Set<K> keySet() {
        return keySet;
    }
    @Override
    public V get(Object o) {
        return (V)findClosest((K)o,root).value;
    }

    int compare(K o1, K o2) {
        return ((Comparable) o1).compareTo(o2);
    }
    Node findClosest(K k,Node root)
    {
        while(root.key!=k)
        {
            if(compare(k,(K)root.key)==1) {
                if(root.right!=null)
                    root = root.right;
                else
                    break;
            }
            else {
                if(root.left!=null)
                    root = root.left;
                else
                    break;
            }
        }
        return root;
    }

    @Override
    public V put(K k, V v) {
        if(root==null)
        {
            size++;
            keySet.add(k);
            root=new Node(k,v,null);
            return v;
        }
        if(root.key==k)
        {
            root.value=v;
            return v;
        }
        Node found = findClosest(k,root);
        if(found.key.equals(k))
        {
            found.value=v;
            return v;
        }
        else if(compare(k,(K)found.key)==1)
        {
            size++;
            keySet.add(k);
            found.right=new Node(k,v,found);
            return v;
        }
        else
        {
            size++;
            keySet.add(k);
            found.left=new Node(k,v,found);
            return v;
        }
    }

    Node largestLowerThan(Node root)
    {
        if(root.left==null)return root;
        root=root.left;
        while(root.right!=null)
            root=root.right;
        return root;
    }
    Node SmallstGreaterThan(Node root)
    {
        if(root.right==null)return root;
        root=root.right;
        while(root.left!=null)
            root=root.left;
        return root;
    }

    @Override
    public V remove(Object o) {
        Node found=findClosest((K)o,root);
        Node replace;
        if(found.parent==null||found==found.parent.right) {
            replace = largestLowerThan(found);
            if(replace==found)
            {
                replace=SmallstGreaterThan(found);
                if(replace==found) {
                    if(found.parent!=null)
                    {
                        found.parent.right=null;
                        size--;
                        keySet.remove(found.key);
                        return (V) found.value;
                    }
                }
            }
        }
        else {
            replace = SmallstGreaterThan(found);
            if(replace==found)
            {
                replace=largestLowerThan(found);
                if(replace==found) {
                    if(found.parent!=null)
                    {
                        found.parent.left=null;
                        size--;
                        return (V) found.value;
                    }
                }
            }
        }
        V toReturn= (V) found.value;
        found.value=replace.value;
        keySet.remove(found.key);
        found.key=replace.key;
        size--;
        if(replace.parent.right==replace) {
            if (replace.left != null)
                replace.parent.right = null;
            else
                replace.parent.right = replace.left;
        }
        else {
            if (replace.right != null)
                replace.parent.left = null;
            else
                replace.parent.left = replace.right;
        }
        return toReturn;
    }
    @Override
    public void clear() {
        root=null;
        size=0;
        keySet=new HashSet<K>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (root==null);
    }

    @Override
    public boolean containsKey(Object o) {
        if(findClosest((K)o,root).key.equals((K)o))return true;
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        for (K k:
             keySet) {
            if(get(k).equals(o))return true;
        };
        return false;
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

    @Override
    public K firstKey() {
        return null;
    }

    @Override
    public K lastKey() {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }


    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
