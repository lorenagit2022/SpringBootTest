package com.evaluation.client.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "PERSONA")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_PERSONA")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "IDENTIFICACION", unique = true, nullable = false)
    private String identify;
    @Column(name = "NOMBRE")
    private String name;
    @Column(name = "GENERO")
    private String gender;
    @Column(name = "EDAD")
    private Integer age;
    @Column(name = "DIRECCION")
    private String direction;
    @Column(name = "TELEFONO")
    private Integer phone;

}
