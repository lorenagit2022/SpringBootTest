package com.evaluation.statementAccount.service.impl;

import com.evaluation.statementAccount.entity.dto.MovementDto;
import com.evaluation.statementAccount.service.MovementRestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovementRestServiceImpl implements MovementRestService {
    private static final Logger logger = LogManager.getLogger(MovementRestServiceImpl.class);
    @Autowired
    private final RestTemplate restTemplate;

    public MovementRestServiceImpl(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<MovementDto> getAccountByIdAccountAndDateJSON(Integer idAccount, String dateStart, String dateEnd) throws IOException {

        String url = "http://localhost:8090/movement/dateBetween/{idAccount}/{start}/{end}";
        logger.info("servicio de movimientos por cuenta y rango de fechas" + idAccount);
        Map<String, String> params = new HashMap<String, String>();
        params.put("idAccount", idAccount.toString());
        params.put("start", dateStart);
        params.put("end", dateEnd);

        ResponseEntity<MovementDto[]> result = this.restTemplate.getForEntity(url, MovementDto[].class, params);
        logger.info("paso");
        return Arrays.asList(result.getBody());

    }
}
