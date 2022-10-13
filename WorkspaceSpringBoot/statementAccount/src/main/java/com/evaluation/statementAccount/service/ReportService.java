package com.evaluation.statementAccount.service;

import com.evaluation.statementAccount.entity.Report;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ReportService {

    public List<Report> getReportStatementAccount(String identification, String dateStart, String dateEnd) throws IOException;
}
