package com.vivek.cache.policies;

import com.vivek.algorithms.DoublyLinkedList;
import com.vivek.algorithms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            var newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        var firstNode = dll.getFirstNode();
        if (firstNode == null) {
            return null;
        }
        dll.detachNode(firstNode);
        mapper.remove(firstNode.getElement());
        return firstNode.getElement();
    }
}
