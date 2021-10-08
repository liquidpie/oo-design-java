package com.vivek.cache.factories;

import com.vivek.cache.Cache;
import com.vivek.cache.policies.EvictionPolicyType;
import com.vivek.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new HashMapBasedStorage<Key, Value>(capacity), EvictionPolicyType.LRU);
    }

    public Cache<Key, Value> lfuCache(final int capacity) {
        return new Cache<Key, Value>(new HashMapBasedStorage<Key, Value>(capacity), EvictionPolicyType.LFU);
    }

}
