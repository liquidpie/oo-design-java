package com.vivek.online.shopping.model;

import com.vivek.online.shopping.model.product.Product;

import java.util.List;
import java.util.Map;

public class Catalog implements Search {

    private Map<String, List<Product>> productNames;
    private Map<String, List<Product>> productCategories;

    @Override
    public List<Product> searchProductsByName(String name) {
        return productNames.get(name);
    }

    @Override
    public List<Product> searchProductByCategory(String category) {
        return productCategories.get(category);
    }
}
