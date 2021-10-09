package com.vivek.rental.car.repository;

import com.vivek.rental.car.exception.AccountDoesNotExistException;
import com.vivek.rental.car.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistException;
}

