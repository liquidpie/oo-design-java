package com.vivek.mqs.db;

import java.util.Map;

public interface Collection<K, V> {

    void insert(K key, V value);

    void update(K key, V newValue);

    V get(K key);

    boolean delete(K key);

    default Map<K, V> getAll() {
        return null;
    }

}
