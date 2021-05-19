package com.vivek.mqs.db;

import java.util.HashMap;
import java.util.Map;

public class SimpleCollection<K, V> implements Collection<K, V> {

    private final String name;
    private final Map<K, V> store;

    public SimpleCollection() {
        this(null);
    }

    public SimpleCollection(String  name) {
        this(name, new HashMap<>());
    }

    public SimpleCollection(String name, Map<K, V> store) {
        this.name = name;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    @Override
    public void insert(K key, V value) {
        this.store.put(key, value);
    }

    @Override
    public void update(K key, V newValue) {
        this.store.replace(key, newValue);
    }

    @Override
    public V get(K key) {
        return this.store.get(key);
    }

    @Override
    public boolean delete(K key) {
        return this.store.remove(key) != null;
    }

    @Override
    public Map<K, V> getAll() {
        return Map.copyOf(store);
    }
}
