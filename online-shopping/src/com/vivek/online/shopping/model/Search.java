package com.vivek.online.shopping.model;

import com.vivek.online.shopping.model.product.Product;

import java.util.List;

public interface Search {

    List<Product> searchProductsByName(String name);

    List<Product> searchProductByCategory(String category);

}
