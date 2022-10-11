package com.evaluation.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "CLIENTE")
public class Client extends Person{
    @Column(name = "CONTRASENA")
    private String password;
    @Column(name = "ESTADO")
    private Boolean status;
}
