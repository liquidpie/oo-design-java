package com.vivek.rental.car.service;

import com.vivek.rental.car.exception.AccountDoesNotExistException;
import com.vivek.rental.car.model.account.Account;
import com.vivek.rental.car.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistException;
}
