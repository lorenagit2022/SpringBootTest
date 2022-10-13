package com.evaluation.statementAccount.controller;

import com.evaluation.statementAccount.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class reportController {
    private static final Logger logger = LogManager.getLogger(reportController.class);

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{identify}/{start}/{end}")
    public ResponseEntity<?> getReport(@PathVariable String identify, @PathVariable String start, @PathVariable String end) {
        try {
            return new ResponseEntity(reportService.getReportStatementAccount(identify, start, end), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
