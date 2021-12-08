package com.vivek.kvstore.model;

public class Query {

    private final String key;
    private final Object value;
    private final Operator operator;

    public Query(String key, Object value, Operator operator) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

//    public static class QueryBuilder {
//        private String key;
//        private String value;
//        private Operator operator;
//
//        public void equals(String key, )
//
//
//    }

}
