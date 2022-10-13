package com.evaluation.movement.service.impl;

import com.evaluation.movement.entities.dto.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestServiceImpl {
    private static final Logger logger = LogManager.getLogger(RestServiceImpl.class);
    @Autowired
    private final RestTemplate restTemplate;


    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AccountDto getAccountByIDJSON(Integer id) throws JsonProcessingException {
        String url = "http://localhost:8089/account/id/{id}";
        logger.info("inicio el consumo service"+id);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("el resultaod es: "+result);
        ObjectMapper mapper = new ObjectMapper();
        AccountDto client = mapper.readValue(result, AccountDto.class);

        return client;
    }

    public AccountDto updateAccountByIDJSON(Integer idAccount, Double balance) throws JsonProcessingException {
        String url = "http://localhost:8089/account/updateBalance/{idAccount}/{balance}";
        logger.info("inicio el consumo service");
        Map<String, String> params = new HashMap<String, String>();
        params.put("idAccount", idAccount.toString());
        params.put("balance", balance.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("el resultaod es: "+result);
        ObjectMapper mapper = new ObjectMapper();
        AccountDto client = mapper.readValue(result, AccountDto.class);

        return client;


    }


}
