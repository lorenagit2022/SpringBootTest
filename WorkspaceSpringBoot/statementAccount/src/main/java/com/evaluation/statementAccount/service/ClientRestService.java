package com.evaluation.statementAccount.service;

import com.evaluation.statementAccount.entity.dto.ClientDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ClientRestService {

    ClientDto getClientByIDJSON(Integer id) throws JsonProcessingException;
    ClientDto getClientByIdentifyJSON(String identify) throws JsonProcessingException;

}
