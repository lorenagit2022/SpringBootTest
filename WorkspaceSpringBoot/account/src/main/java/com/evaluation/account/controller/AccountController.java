package com.evaluation.account.controller;

import com.evaluation.account.entities.Account;
import com.evaluation.account.exception.AccountException;
import com.evaluation.account.exception.AccountNotFoundException;
import com.evaluation.account.service.AccountService;
import com.evaluation.account.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/")
    public List<Account> getAccounts(){
        return  accountService.findAllAccounts();
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity getAccountByAccountNumber(@PathVariable Integer accountNumber){
        try {
            return new ResponseEntity(accountService.findByAccountNumber(accountNumber), HttpStatus.OK);
        } catch (AccountNotFoundException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/account/create/")
    public ResponseEntity createAccount(@RequestBody Account account, @RequestBody Integer identify){
        try {
            logger.info("identifiacion: "+identify);
            return new ResponseEntity(accountService.createAccount(account, identify), HttpStatus.OK);
        } catch (AccountException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/account/update")
    public ResponseEntity updateAccount(@RequestBody Account account){
        try {
            return new ResponseEntity(accountService.updateAccount(account), HttpStatus.OK);
        } catch (AccountNotFoundException  mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/activateAccount/{accountNumber}")
    public ResponseEntity activateAccount(@PathVariable Integer accountNumber){
        try {
            return new ResponseEntity(accountService.updateAccountState(accountNumber, true), HttpStatus.OK);
        } catch (AccountNotFoundException  mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/deactivateAccount/{accountNumber}")
    public ResponseEntity deactivateAccount(@PathVariable Integer accountNumber){
        try {
            return new ResponseEntity(accountService.updateAccountState(accountNumber, false), HttpStatus.OK);
        } catch (AccountNotFoundException  mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/delete/{accountNumber}")
    public ResponseEntity deleteAccount(@PathVariable Integer accountNumber){
        try {
            return new ResponseEntity(accountService.deleteAccountByAccountNumber(accountNumber), HttpStatus.OK);
        } catch (AccountNotFoundException  mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
