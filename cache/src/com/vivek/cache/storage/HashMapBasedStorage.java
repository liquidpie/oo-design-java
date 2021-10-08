package com.vivek.cache.storage;

import com.vivek.cache.exceptions.NotFoundException;
import com.vivek.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {

    private final Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) {
            throw new StorageFullException();
        }
        storage.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) {
            throw new NotFoundException();
        }
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) {
            throw new NotFoundException();
        }
        storage.remove(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
