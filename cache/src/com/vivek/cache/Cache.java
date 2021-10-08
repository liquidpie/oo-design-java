package com.vivek.cache;

import com.vivek.cache.exceptions.NotFoundException;
import com.vivek.cache.exceptions.StorageFullException;
import com.vivek.cache.policies.EvictionPolicy;
import com.vivek.cache.policies.EvictionPolicyType;
import com.vivek.cache.policies.LFUEvictionPolicy;
import com.vivek.cache.policies.LRUEvictionPolicy;
import com.vivek.cache.storage.Storage;

public class Cache<Key, Value> {

    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicyType evictionPolicyType) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicyType == EvictionPolicyType.LRU ? new LRUEvictionPolicy<>() : new LFUEvictionPolicy<>();
    }

    public void put(Key key, Value value) {
        try {
            storage.add(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException storageFullException) {
            System.out.println("Got Storage full for key: " + key + ". Will try to evict");
            var keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new IllegalStateException("Storage full and no key to evict");
            }
            storage.remove(keyToRemove);
            System.out.println("Creating space by evicting key: " + keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            var value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to access non-existing node");
            return null;
        }
    }

}
