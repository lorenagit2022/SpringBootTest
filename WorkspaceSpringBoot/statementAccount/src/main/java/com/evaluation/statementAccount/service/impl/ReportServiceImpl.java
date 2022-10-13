package com.evaluation.statementAccount.service.impl;

import com.evaluation.statementAccount.entity.Report;
import com.evaluation.statementAccount.entity.dto.AccountDto;
import com.evaluation.statementAccount.entity.dto.ClientDto;
import com.evaluation.statementAccount.entity.dto.MovementDto;
import com.evaluation.statementAccount.service.AccountRestService;
import com.evaluation.statementAccount.service.ClientRestService;
import com.evaluation.statementAccount.service.MovementRestService;
import com.evaluation.statementAccount.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LogManager.getLogger(ReportServiceImpl.class);
    @Autowired
    private MovementRestService movementRestService;

    @Autowired
    private AccountRestService accountRestService;

    @Autowired
    private ClientRestService clientRestService;

    @Override
    public List<Report> getReportStatementAccount(String identification, String dateStart, String dateEnd) throws IOException {

        ClientDto client = clientRestService.getClientByIdentifyJSON(identification);
        logger.info("cliente: " + client.getId());
        List<AccountDto> listAccount = accountRestService.getAccountByIdClientJSON(client.getId());
        List<Report> listReport = new ArrayList<>();
        logger.info("lista cuentas :" + listAccount.size());
        for (AccountDto account : listAccount) {
            logger.info("cuenta: " + account.getId());
            List<MovementDto> listMovement = movementRestService.getAccountByIdAccountAndDateJSON(account.getId(), dateStart, dateEnd);
            if (listMovement.size() > 0) {
                for (MovementDto movement : listMovement) {
                    logger.info("movimeinto: " + movement.getId());
                    Report report = new Report(null, movement.getDate(), client.getName(), account.getAccountNumber(), account.getAccountType(), account.getBalance(), account.getStatus(), movement.getMovementType(), movement.getValue(), movement.getAvailableBalance());
                    listReport.add(report);
                }
            }
        }

        return listReport;
    }
}
