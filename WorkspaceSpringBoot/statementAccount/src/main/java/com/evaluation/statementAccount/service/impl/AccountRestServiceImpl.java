package com.evaluation.statementAccount.service.impl;

import com.evaluation.statementAccount.entity.dto.AccountDto;
import com.evaluation.statementAccount.service.AccountRestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountRestServiceImpl implements AccountRestService {
    private static final Logger logger = LogManager.getLogger(AccountRestServiceImpl.class);
    @Autowired
    private final RestTemplate restTemplate;

    public AccountRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountDto getAccountByIDJSON(Integer id) throws JsonProcessingException {
        String url = "http://localhost:8089/account/id/{id}";
        logger.info("servicio de cuenta" + id);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("cuenta es: " + result);
        ObjectMapper mapper = new ObjectMapper();
        AccountDto client = mapper.readValue(result, AccountDto.class);

        return client;
    }

    @Override
    public List<AccountDto> getAccountByIdClientJSON(Integer idClient) throws JsonProcessingException {
        String url = "http://localhost:8089/account/idClient/{idClient}";
        ObjectMapper mapper = new ObjectMapper();
        logger.info("servicio de cuentas por id cliente" + idClient);
        Map<String, String> params = new HashMap<String, String>();
        params.put("idClient", idClient.toString());
        ResponseEntity<AccountDto[]> result = this.restTemplate.getForEntity(url, AccountDto[].class, params);
        logger.info("paso");
        return Arrays.asList(result.getBody());
    }
}
