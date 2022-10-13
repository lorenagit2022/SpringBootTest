package com.evaluation.movement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "MOVIMIENTO")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private Integer id;
    @Column(name = "TIPO_MOVIMIENTO")
    private String movementType;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_MOVIMIENTO")
    private Date Date;
    @Column(name = "VALOR")
    private Double value;
    @Column(name = "SALDO_DISPONIBLE")
    private Double availableBalance;
    @Column(name = "ID_CUENTA")
    private Integer idAccount;
    @Column(name = "ES_ULTIMO")
    private Boolean isLast;
    @Transient
    Integer numberAccount;
    @Transient
    String nameClient;
}
