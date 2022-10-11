package com.evaluation.account.service.impl;

import com.evaluation.account.entities.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl {

    private final RestTemplate restTemplate;


    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ClientDto getClientIDByIdentificationJSON(Integer identify) {
        String url = "http://localhost:8088/client/{identify}";
        return this.restTemplate.getForObject(url, ClientDto.class);
    }


}
