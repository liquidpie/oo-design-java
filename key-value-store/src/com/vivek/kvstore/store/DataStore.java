package com.vivek.kvstore.store;

import com.vivek.kvstore.model.DataType;
import com.vivek.kvstore.model.IndexedData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    public static Map<String, Map<String, Object>> RECORDS = new ConcurrentHashMap<>();
    public static Map<String, DataType> ATTR_DATATYPES = new ConcurrentHashMap<>();
    public static Set<String> INDEX_KEYS = new HashSet<>();

    public static Map<String, Map<Object, IndexedData<Object>>> INDEX = new HashMap<>();

}
