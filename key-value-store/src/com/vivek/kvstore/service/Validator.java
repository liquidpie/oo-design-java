package com.vivek.kvstore.service;

import com.vivek.interview.exception.AttributeEmptyException;
import com.vivek.interview.exception.InvalidDataTypeException;
import com.vivek.interview.exception.InvalidKeyException;
import com.vivek.interview.model.DataType;
import com.vivek.interview.model.Value;
import com.vivek.interview.store.DataStore;

import java.util.List;

public class Validator {

      public void validate(String key) {
          if (key == null || key.length() == 0) {
              throw new InvalidKeyException();
          }
      }

      public void validate(List<Value> values) {
          if (values == null || values.isEmpty()) {
              throw new AttributeEmptyException();
          }

          for (Value value : values) {
              if (value.getAttribute() == null || value.getData() == null) {
                  throw new IllegalArgumentException();
              }

              DataType dataType = DataStore.ATTR_DATATYPES.get(value.getAttribute());
              if (dataType != null) {
                  validate(value.getData(), dataType);
              }
          }

      }

      public void validate(Object data, DataType dataType) {
          switch (dataType) {
              case DOUBLE:
                  if (!(data instanceof Double)) {
                      throw new InvalidDataTypeException();
                  }
                  break;
              case STRING:
                  if (!(data instanceof String)) {
                      throw new InvalidDataTypeException();
                  }
                  break;
              case BOOLEAN:
                  if (!(data instanceof Boolean)) {
                      throw new InvalidDataTypeException();
                  }
                  break;
              case INTEGER:
                  if (!(data instanceof Integer)) {
                      throw new InvalidDataTypeException();
                  }
                  break;
          }
      }

}
