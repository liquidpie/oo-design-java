package com.vivek.kvstore.utils;

import com.vivek.interview.model.DataType;

public class TypeInferer {

    public static DataType getType(Object data) {
        if (data instanceof Double) {
            return DataType.DOUBLE;
        } else if (data instanceof String) {
            return DataType.STRING;
        } else if (data instanceof Integer) {
            return DataType.INTEGER;
        } else if (data instanceof Boolean) {
            return DataType.BOOLEAN;
        }

        return null;

    }

}
