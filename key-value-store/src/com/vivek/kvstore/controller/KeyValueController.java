package com.vivek.kvstore.controller;

import com.vivek.interview.model.Query;
import com.vivek.interview.model.Value;
import com.vivek.interview.service.KeyValueStoreService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyValueController {

    private final KeyValueStoreService keyValueStoreService;

    public KeyValueController(KeyValueStoreService keyValueStoreService) {
        this.keyValueStoreService = keyValueStoreService;
    }

    public void put(String key, List<Value> values) {
        keyValueStoreService.add(key, values);
    }

    public Map<String, Object> get(String key) {
        return keyValueStoreService.fetch(key);
    }

    public void delete(String key) {
        keyValueStoreService.remove(key);
    }

    public Set<String> allKeys() {
        return keyValueStoreService.getAllKeys();
    }

    public void addSecondaryIndex(String secondaryIndexKey) {
        keyValueStoreService.addSecIndex(secondaryIndexKey);
    }

    public Set<String> executeQuery(Query query) {
        return keyValueStoreService.runQuery(query);
    }

}
