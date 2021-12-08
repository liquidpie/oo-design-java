package com.vivek.kvstore.service;

import com.vivek.interview.model.DataType;
import com.vivek.interview.model.IndexedData;
import com.vivek.interview.store.DataStore;

import java.util.Map;
import java.util.TreeMap;

public class SecondaryIndexBuilder {

    private final Validator validator;

    public SecondaryIndexBuilder(Validator validator) {
        this.validator = validator;
    }

    public void buildIndex(String secondaryIndexKey) {
        validator.validate(secondaryIndexKey);
        if (!DataStore.INDEX_KEYS.contains(secondaryIndexKey) && DataStore.ATTR_DATATYPES.containsKey(secondaryIndexKey)) {
            DataStore.INDEX_KEYS.add(secondaryIndexKey);
            DataType dataType = DataStore.ATTR_DATATYPES.get(secondaryIndexKey);

            buildIndex(secondaryIndexKey, DataStore.RECORDS);
//            if (dataType == DataType.DOUBLE) {
//                buildDoubleIndex(secondaryIndexKey, DataStore.RECORDS);
//            }
//            } else if (dataType == DataType.STRING) {
//                buildDoubleIndex();
//            } else if (dataType == DataType.INTEGER) {
//                buildDoubleIndex();
//            } else if (dataType == DataType.STRING) {
//                buildDoubleIndex();
//            } else {
//                buildBooleanIndex();
//            }


        }
    }

//    private void buildDoubleIndex(String secondaryIndexKey, Map<String, Map<String, Object>> records) {
//        Map<String, Map<Double, IndexedData<Double>>> index = DataStore.DOUBLE_INDEX;
//        Map<Double, IndexedData<Double>> map = index.getOrDefault(secondaryIndexKey, new TreeMap<>());
//
//        for (Map.Entry<String, Map<String, Object>> entry : records.entrySet()) {
//            String key = entry.getKey();
//            for (Map.Entry<String, Object> subEntry : entry.getValue().entrySet()) {
//                if (!subEntry.getKey().equals(secondaryIndexKey)) {
//                    continue;
//                }
//
//                Double data = (Double) subEntry.getValue();
//
//                IndexedData<Double> indexedData = map.getOrDefault(data, new IndexedData<>(data));
//                indexedData.addKey(key);
//
//                map.put(data, indexedData);
//            }
//
//        }
//
//        index.put(secondaryIndexKey, map);
//    }

    private <T> void buildIndex(String secondaryIndexKey, Map<String, Map<String, Object>> records) {
        Map<String, Map<Object, IndexedData<Object>>> index = DataStore.INDEX;
        Map<Object, IndexedData<Object>> map = index.getOrDefault(secondaryIndexKey, new TreeMap<>());

        for (Map.Entry<String, Map<String, Object>> entry : records.entrySet()) {
            String key = entry.getKey();
            for (Map.Entry<String, Object> subEntry : entry.getValue().entrySet()) {
                if (!subEntry.getKey().equals(secondaryIndexKey)) {
                    continue;
                }

                Object data = entry.getValue();

                IndexedData<Object> indexedData = map.getOrDefault(data, new IndexedData<>(data));
                indexedData.addKey(key);

                map.put(data, indexedData);
            }

        }

        index.put(secondaryIndexKey, map);
    }

}
