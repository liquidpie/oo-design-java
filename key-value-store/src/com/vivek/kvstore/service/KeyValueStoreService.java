package com.vivek.kvstore.service;

import com.vivek.kvstore.model.IndexedData;
import com.vivek.kvstore.model.Query;
import com.vivek.kvstore.model.Value;
import com.vivek.kvstore.store.DataStore;
import com.vivek.kvstore.utils.TypeInferer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyValueStoreService {

    private final SecondaryIndexBuilder secondaryIndexBuilder;
    private final Validator validator;

    public KeyValueStoreService(SecondaryIndexBuilder secondaryIndexBuilder, Validator validator) {
        this.secondaryIndexBuilder = secondaryIndexBuilder;
        this.validator = validator;
    }

    public void add(String key, List<Value> values) {
        validator.validate(key);
        validator.validate(values);

        for (Value value : values) {
            if (!DataStore.ATTR_DATATYPES.containsKey(value.getAttribute())) {
                DataStore.ATTR_DATATYPES.put(value.getAttribute(), TypeInferer.getType(value.getData()));
            }
            if (DataStore.INDEX_KEYS.contains(value.getAttribute())) {
                Map<Object, IndexedData<Object>> indexedDataMap = DataStore.INDEX.getOrDefault(value.getAttribute(), new HashMap<>());
                IndexedData<Object> indexedData = indexedDataMap.getOrDefault(value.getData(), new IndexedData<>(value.getData()));
                indexedData.addKey(key);
                DataStore.INDEX.put(value.getAttribute(), indexedDataMap);
            }
        }

        DataStore.RECORDS.put(key, values.stream().collect(Collectors.toMap(Value::getAttribute, Value::getData)));

    }

    public Map<String, Object> fetch(String key) {
        return DataStore.RECORDS.getOrDefault(key, Map.of());
    }

    public void remove(String key) {
        Map<String, Object> record = DataStore.RECORDS.get(key);
        if (record != null) {
            for (String attr : record.keySet()) {
                if (DataStore.INDEX_KEYS.contains(attr)) {
                    Map<Object, IndexedData<Object>> indexedDataMap = DataStore.INDEX.get(attr);
                    if (indexedDataMap != null) {
                        IndexedData<Object> indexedData = indexedDataMap.get(record.get(attr));
                        if (indexedData != null) {
                            indexedData.getKeys().remove(key);
                        }
                    }
                }
            }
        }

        DataStore.RECORDS.remove(key);

    }

    public Set<String> getAllKeys() {
        return DataStore.RECORDS.keySet();
    }

    public void addSecIndex(String secondaryIndexKey) {
        secondaryIndexBuilder.buildIndex(secondaryIndexKey);
    }

    public Set<String> runQuery(Query query) {

    }
}
