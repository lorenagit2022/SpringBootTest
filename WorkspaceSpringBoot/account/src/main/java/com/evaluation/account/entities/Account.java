package com.evaluation.account.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "CUENTA")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private Integer id;
    @Column(name = "NUMERO_CUENTA", unique = true, nullable = false)
    private Integer accountNumber;
    @Column(name = "TIPO_CUENTA")
    private String accountType;
    @Column(name = "SALDO_INICIAL")
    private Double balance;
    @Column(name = "ESTADO")
    private Boolean status;
    @Column(name = "ID_CLIENTE")
    private String idClient;
}
