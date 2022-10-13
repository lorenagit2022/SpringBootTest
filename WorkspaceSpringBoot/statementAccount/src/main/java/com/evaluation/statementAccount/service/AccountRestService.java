package com.evaluation.statementAccount.service;

import com.evaluation.statementAccount.entity.dto.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AccountRestService {

    AccountDto getAccountByIDJSON(Integer id) throws JsonProcessingException;

    List<AccountDto> getAccountByIdClientJSON(Integer idClient) throws JsonProcessingException;
}
