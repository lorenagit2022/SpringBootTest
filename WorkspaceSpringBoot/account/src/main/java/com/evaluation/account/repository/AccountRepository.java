package com.evaluation.account.repository;

import com.evaluation.account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByAccountNumber(Integer numberAccount);

    List<Account> findAccountsByIdClient(Integer idClient);

    Optional<Account> findById(Integer id);

    Account save(Account account);

    void deleteAccountsByAccountNumber(Integer numberAccount);

}
