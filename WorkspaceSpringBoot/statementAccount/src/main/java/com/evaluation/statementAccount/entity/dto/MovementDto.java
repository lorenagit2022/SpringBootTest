package com.evaluation.statementAccount.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDto {
    private Integer id;
    private String movementType;
    private java.util.Date Date;
    private Double value;
    private Double availableBalance;
    private Integer idAccount;
    private Boolean isLast;
}
