package com.evaluation.account.service.impl;

import com.evaluation.account.entities.Account;
import com.evaluation.account.entities.dto.ClientDto;
import com.evaluation.account.exception.AccountException;
import com.evaluation.account.exception.AccountNotFoundException;
import com.evaluation.account.repository.AccountRepository;
import com.evaluation.account.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestServiceImpl restService;


    @Override
    public Optional<Account> findByAccountNumber(Integer accountNumber) throws AccountNotFoundException {
        logger.info("accountNumber" + accountNumber);
        Optional<Account> account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (Objects.isNull(account) || account.isEmpty()) {
            throw new AccountNotFoundException(accountNumber.toString());
        }
        return account;
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account, Integer identify) throws AccountException {
        try {
            ClientDto client = restService.getClientIDByIdentificationJSON(identify);
            logger.info("identifiacion"+identify);
            return accountRepository.save(account);
        } catch (Exception e) {
            throw new AccountException("No se puede guardar", e);
        }
    }

    @Override
    public Account updateAccount(Account account) throws AccountNotFoundException {
        Optional<Account> accountResult = accountRepository.findAccountByAccountNumber(account.getAccountNumber());
        if (accountResult.isPresent()) {
            return accountRepository.save(accountResult.get());
        } else {
            throw new AccountNotFoundException(account.getAccountNumber().toString());
        }
    }


    @Override
    @Transactional
    public Account deleteAccountByAccountNumber(Integer accountNumber) throws AccountNotFoundException {
        Optional<Account> accountResult = accountRepository.findAccountByAccountNumber(accountNumber);
        if (accountResult.isPresent()) {
            accountRepository.deleteAccountsByAccountNumber(accountNumber);

        } else {
            throw new AccountNotFoundException(accountNumber.toString());
        }
        return accountResult.get();
    }

    @Override
    public Account updateAccountState(Integer accountNumber, Boolean status) throws AccountNotFoundException {
        {
            logger.info("accountNumber" + accountNumber);
            Optional<Account> account = this.findByAccountNumber(accountNumber);
            if (account.isPresent()) {
                account.get().setStatus(status);
                return accountRepository.save(account.get());
            } else {
                throw new AccountNotFoundException(accountNumber.toString());
            }
        }
    }
}
