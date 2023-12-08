package com.smart.smartapp;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/8/25
 */
public class CustomMap<K, V> {

    private K key;

    private V value;

    public V get(K key) {
        if (this.key.equals(key)) {
            return value;
        }
        return null;
    }

    public void put(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
