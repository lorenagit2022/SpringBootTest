package com.evaluation.statementAccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date movementDate;
    private String nameClient;
    private Integer accountNumber;
    private String accountType;
    private Double balance;
    private Boolean accountState;
    private String movementType;
    private Double value;
    private Double movementBBalance;


}
