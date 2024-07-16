package com.smart.smartapp;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/8/25
 */
public class CustomMap<K, V> {

    // 初始化容量 16;必须是2的幂
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    /**
     * The load factor used when none specified in constructor.
     * 默认荷载系数
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The next size value at which to resize (capacity * load factor). 默认12
     * 初始化时，阈值是2次幂且大于或等于一个用户自定义的 capacity
     * 一旦当前元素数量大于threshold，即重构哈希map, 新的capacity=旧的*2（newCap = oldCap << 1）；新的threshold=新的capacity*loadFactor  【(float)newCap * loadFactor;】
     */
    int threshold;

    int capacity;

    /**
     * The load factor for the hash table.
     */
    final float loadFactor;

    // transient 短暂 不可序列化，当对象序列化后传输时，该元素值不被序列化传输
    transient Node<K, V>[] table;

    // 元素数量
    transient int size;

    public CustomMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * loadFactor);
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    public CustomMap(float loadFactor, int capacity) {
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        Integer hash = hash(key);

        if (table == null) {
            table = (Node<K, V>[]) new Node[capacity];
        }
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            table[index] = new Node<>(hash, key, value, null);
        } else {
            table[index].value = value;
        }

        if (++size > threshold) {
            resize();
        }

    }

    public V get(K key) {
        Integer hash = hash(key);

        Node<K, V> first = table[hash & (table.length - 1)];
        if (first != null && first.hash == hash && first.key.equals(key)) {
            return first.value;
        }
        return null;
    }

    private void resize() {
        Node<K, V>[] oldTab = table;
        int oldCapacity = capacity;
        // *2
        capacity = capacity << 1;
        threshold = (int) (capacity * loadFactor);
        Node<K, V>[] newTab = new Node[capacity];
        table = newTab;
        for (int j = 0; j < oldCapacity; j++) {
            Node<K, V> e;
            if ((e = oldTab[j]) != null) {
                newTab[e.hash & (capacity - 1)] = e;
            }
        }
    }

    private Integer hash(K key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
