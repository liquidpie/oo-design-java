package com.vivek.kvstore.model;

public class Value {

    String attribute;
    Object data;

    private Value(String attribute, Object data) {
        this.attribute = attribute;
        this.data = data;
    }

    public static Value of(String attribute, Object data) {
        return new Value(attribute, data);
    }

    public String getAttribute() {
        return attribute;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Value{" +
                "attribute='" + attribute + '\'' +
                ", data=" + data +
                '}';
    }
}
