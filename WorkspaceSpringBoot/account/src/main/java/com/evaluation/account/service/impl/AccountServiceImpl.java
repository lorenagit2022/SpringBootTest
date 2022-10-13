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
    public Optional<Account> findByAccountNumber(Integer accountNumber) throws AccountNotFoundException, AccountException {
        logger.info("accountNumber" + accountNumber);
        Optional<Account> account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (Objects.isNull(account) || account.isEmpty()) {
            throw new AccountNotFoundException(accountNumber.toString());
        }
        return setNameClient(account);
    }

    @Override
    public List<Account> findByIdClient(Integer idClient) throws AccountNotFoundException {
        logger.info("idClient" + idClient);
        List<Account> listAccount = accountRepository.findAccountsByIdClient(idClient);
        if (listAccount.size() == 0) {
            throw new AccountNotFoundException(idClient.toString());
        }
        return listAccount;
    }

    @Override
    public List<Account> findAllAccounts() {

        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findByID(Integer id) throws AccountNotFoundException, AccountException {
        logger.info("valor inicialid:" + id);
        Optional<Account> account = accountRepository.findById(id);
        if (Objects.isNull(account) || account.isEmpty()) {
            throw new AccountNotFoundException(id.toString());
        }
        return setNameClient(account);

    }

    @Override
    public Account createAccount(Account account) throws AccountException {
        try {
            ClientDto client = restService.getClientByIDJSON(account.getIdClient());
            account.setNameClient(client.getName());
            account.setBalance(0.0);
            return accountRepository.save(account);
        } catch (Exception e) {
            throw new AccountException("No se puede guardar" + e.getMessage(), e);
        }
    }

    @Override
    public Account updateAccount(Account account) throws AccountNotFoundException {
      try {
          Account accountResult = accountRepository.save(account);
          accountResult = this.setNameClient(Optional.ofNullable(accountResult)).get();
          return accountResult;
      }catch(Exception e){
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
    public Account updateAccountState(Integer accountNumber, Boolean status) throws AccountNotFoundException, AccountException {
        {
            Optional<Account> account = accountRepository.findAccountByAccountNumber(accountNumber);
            if (account.isPresent()) {
                account.get().setStatus(status);
                Account accountResult = accountRepository.save(account.get());
                accountResult = this.setNameClient(Optional.ofNullable(accountResult)).get();
                return accountResult;
            } else {
                throw new AccountNotFoundException(accountNumber.toString());
            }
        }
    }

    @Override
    public Account updateBalanceByIdAccount(Integer id, Double balance) throws AccountNotFoundException, AccountException {
        Optional<Account> account = this.findByID(id);
        account.get().setBalance(balance);
        return this.updateAccount(account.get());
    }

    public Optional<Account> setNameClient(Optional<Account> account) throws AccountException {
        try {
            ClientDto client = restService.getClientByIDJSON(account.get().getIdClient());
            account.get().setNameClient(client.getName());
            logger.info("nombre cliente:"+client.getName());
            return account;
        } catch (Exception e) {
            throw new AccountException("Problemas cargando el cliente" + e.getMessage(), e);
        }
    }

}
