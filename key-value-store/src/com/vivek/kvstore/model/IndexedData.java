package com.vivek.kvstore.model;

import java.util.ArrayList;
import java.util.List;

public class IndexedData<T> {

    private final T data;
    private final List<String> keys;

    public IndexedData(T data) {
        this.data = data;
        this.keys = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void addKey(String key) {
        keys.add(key);
    }

}
