package com.evaluation.account.service;

import com.evaluation.account.entities.Account;
import com.evaluation.account.exception.AccountException;
import com.evaluation.account.exception.AccountNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findByAccountNumber(Integer accountNumber) throws AccountNotFoundException, AccountException;

    List<Account> findByIdClient(Integer idClient) throws AccountNotFoundException;

    List<Account> findAllAccounts();

    Optional<Account> findByID(Integer id) throws AccountNotFoundException, AccountException;

    Account createAccount(Account account) throws AccountException;

    Account updateAccount(Account account) throws AccountNotFoundException;

    Account deleteAccountByAccountNumber(Integer accountNumber) throws AccountNotFoundException;

    Account updateAccountState(Integer accountNumber, Boolean status) throws AccountNotFoundException, AccountException;

    Account updateBalanceByIdAccount(Integer id, Double balance) throws AccountNotFoundException, AccountException;
}
