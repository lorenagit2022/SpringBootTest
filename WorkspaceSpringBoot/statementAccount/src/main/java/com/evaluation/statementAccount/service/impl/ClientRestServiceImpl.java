package com.evaluation.statementAccount.service.impl;

import com.evaluation.statementAccount.entity.dto.ClientDto;
import com.evaluation.statementAccount.service.ClientRestService;
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
public class ClientRestServiceImpl implements ClientRestService {
    private static final Logger logger = LogManager.getLogger(ClientRestServiceImpl.class);
    @Autowired
    private final RestTemplate restTemplate;

    public ClientRestServiceImpl(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ClientDto getClientByIDJSON(Integer id) throws JsonProcessingException {
        String url = "http://localhost:8088/client/id/{id}";
        logger.info("servicio cliente"+id);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("cliente es: "+result);
        ObjectMapper mapper = new ObjectMapper();
        ClientDto client = mapper.readValue(result, ClientDto.class);

        return client;
    }

    @Override
    public ClientDto getClientByIdentifyJSON(String identify) throws JsonProcessingException {
        String url = "http://localhost:8088/client/activateClient/{identify}";
        logger.info("servicio cliente por identificacion"+identify);
        Map<String, String> params = new HashMap<String, String>();
        params.put("identify", identify.toString());
        String result = this.restTemplate.getForObject(url, String.class, params);
        logger.info("cliente es: "+result);
        ObjectMapper mapper = new ObjectMapper();
        ClientDto client = mapper.readValue(result, ClientDto.class);

        return client;
    }

}
