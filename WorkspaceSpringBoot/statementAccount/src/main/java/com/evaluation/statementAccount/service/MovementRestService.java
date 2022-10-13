package com.evaluation.statementAccount.service;

import com.evaluation.statementAccount.entity.dto.MovementDto;

import java.io.IOException;
import java.util.List;

public interface MovementRestService {

    List<MovementDto> getAccountByIdAccountAndDateJSON(Integer idAccount, String dateStart, String dateEnd) throws IOException;
}
