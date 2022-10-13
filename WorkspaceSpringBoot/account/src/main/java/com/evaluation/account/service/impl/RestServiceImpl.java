package com.evaluation.account.service.impl;

import com.evaluation.account.entities.dto.ClientDto;
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

    public ClientDto getClientByIDJSON(Integer id) throws JsonProcessingException {
        String url = "http://localhost:8088/client/id/{id}";
        logger.info("inicio el consumo service"+id);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("el resultado es: "+result);
        ObjectMapper mapper = new ObjectMapper();
        ClientDto client = mapper.readValue(result, ClientDto.class);

        return client;
    }


}
