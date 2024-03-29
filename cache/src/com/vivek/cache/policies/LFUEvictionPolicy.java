package com.vivek.cache.policies;

import com.vivek.algorithms.AccessCountNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final PriorityQueue<AccessCountNode<Key>> counter;
    private final Map<Key, AccessCountNode<Key>> mapper;  // this map will not be required if merged with storage map

    public LFUEvictionPolicy() {
        this.counter = new PriorityQueue<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            var node = mapper.get(key);
            counter.remove(node);
            node.accessed();
            counter.add(node);
        } else {
            var node = new AccessCountNode<>(key);
            counter.add(node);
            mapper.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        var firstNode = counter.poll();
        if (firstNode == null) {
            return null;
        }
        mapper.remove(firstNode.getValue());
        return firstNode.getValue();
    }

}
