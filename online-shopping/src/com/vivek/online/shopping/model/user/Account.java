package com.vivek.online.shopping.model.user;

import com.vivek.online.shopping.model.payment.BankAccount;
import com.vivek.online.shopping.model.payment.CreditCard;
import com.vivek.online.shopping.model.product.Product;
import com.vivek.online.shopping.model.product.ProductReview;

import java.util.List;

public class Account {

    private String userName;
    private String password;
    private AccountStatus status = AccountStatus.ACTIVE;
    private String name;
    private Address shippingAddress;
    private String phone;
    private String email;
    private List<CreditCard> creditCards;
    private List<BankAccount> bankAccounts;

    void addProduct(Product product) { }

    void addProductReview(ProductReview review) { }

    void resetPassword() { }
}
