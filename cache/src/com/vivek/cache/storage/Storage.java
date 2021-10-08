package com.vivek.cache.storage;

import com.vivek.cache.exceptions.NotFoundException;
import com.vivek.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {

    void add(Key key, Value value) throws StorageFullException;

    Value get(Key key) throws NotFoundException;

    void remove(Key key) throws NotFoundException;

}
