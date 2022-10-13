package com.evaluation.movement.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
