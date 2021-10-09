package com.vivek.rental.car.service;

import com.vivek.rental.car.exception.AccountDoesNotExistException;
import com.vivek.rental.car.model.account.Account;
import com.vivek.rental.car.model.account.AccountType;
import com.vivek.rental.car.repository.AccountRepository;
import com.vivek.rental.car.repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        return accountRepository.createAccount(account);
    }

    public void resetPassword(String userId, String password,
                              AccountType accountType) throws AccountDoesNotExistException {
        AccountRepository accountRepository =
                AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}
