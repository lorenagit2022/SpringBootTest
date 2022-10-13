package com.evaluation.statementAccount.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer id;
    private Integer accountNumber;
    private String accountType;
    private Double balance;
    private Boolean status;
    private Integer idClient;
    private String nameClient;
}
