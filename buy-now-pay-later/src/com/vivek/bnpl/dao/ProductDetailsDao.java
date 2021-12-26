package com.vivek.bnpl.dao;

import com.vivek.bnpl.database.Database;
import com.vivek.bnpl.domain.Product;

public class ProductDetailsDao {

    public void addProductDetails(String productId, Product product) {
        Database.PRODUCT_DETAILS.put(productId, product);
    }

    public void removeProductDetails(String productId) {
        Database.PRODUCT_DETAILS.remove(productId);
    }

    public void getProductDetails(String productId) {
        Database.PRODUCT_DETAILS.get(productId);
    }

}
