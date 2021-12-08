package com.vivek.kvstore.client;

import com.vivek.kvstore.model.Value;

import java.util.ArrayList;
import java.util.List;

public class KeyValueClient {

    public static ValueBuilder valueBuilder() {
        return new ValueBuilder();
    }

    public static class ValueBuilder {
        private final List<Value> values;

        private ValueBuilder() {
            this.values = new ArrayList<>();
        }

        public ValueBuilder addAttribute(String attribute, Object data) {
            values.add(Value.of(attribute, data));
            return this;
        }

        public List<Value> build() {
            return this.values;
        }
    }

}
